package com.aura.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <R> makeApiCall(
    call: suspend () -> Result<R>,
    errorMessage: Int = 4567
) = try {
    call()
} catch (timeOut: SocketTimeoutException) {
    Result.Error(CallException(1000, "Check internet connection"))
} catch (timeOut: UnknownHostException) {
    Result.Error(CallException(1000, "Check internet connection"))
} catch (e: Exception) {
    Result.Error(CallException(errorMessage = e.message, errorCode = errorMessage))
}

inline fun <ResultModel, RequestModel> networkBoundService(
    crossinline query: () -> Flow<ResultModel>,
    crossinline fetch: suspend () -> Result<RequestModel>,
    crossinline saveFetchResult: suspend (ResultModel) -> Unit,
    crossinline responseMapper: (RequestModel) -> ResultModel,
    shouldFetch: Boolean = true
)= flow{

    if(!shouldFetch) {
        val data = query().firstOrNull()
        if(data != null){
            emit(Result.Success(data))
        }else{
            emit(Result.Error<ResultModel>(CallException(1000, "No data in cache")))
        }
        return@flow
    }

    when(val fetchData = fetch()) {
        is Result.Success -> {
            if (fetchData.data != null) {
                saveFetchResult(responseMapper(fetchData.data))
                emit(Result.Success(query().first()))
            } else {
                val data = query().firstOrNull()
                if (data != null) {
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error<ResultModel>(CallException(1000, "No data in cache")))
                }
            }
        }
        is Result.Error -> {
            val data = query().firstOrNull()
            if(data != null){
                emit(Result.Success(data))
            }else{
                emit(Result.Error<ResultModel>(fetchData.errors))
            }
        }
    }

}

//useful for non-cacheable operations
suspend fun <R> analyzeResponse(
    response: Response<R>,
    collectErrorBody: Boolean = false
): Result<R> {
    return when {
        response.isSuccessful && response.code() == 200 -> {
            Result.Success(response.body())
        }
        response.code() in 400..500 -> {
            if (collectErrorBody) {
                try {
                    var errorBodyJsonString: String?
                    withContext(Dispatchers.Default) {
                        errorBodyJsonString = response.errorBody()?.string()
                    }

                    Result.Error(
                        CallException(
                            response.code(),
                            response.message(),
                            errorBodyJsonString
                        )
                    )
                } catch (e: Exception) {
                    Result.Error(CallException(response.code(), e.message))
                }
            }else{
                Result.Error(CallException(response.code(), response.message()))
            }
        }
        else -> {
            Result.Error(CallException(response.code(), response.message()))
        }
    }
}

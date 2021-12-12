package com.aura.portfolio.ui.fragment.dashboard

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aura.core.BaseViewHolder
import com.aura.domain.model.*
import com.aura.portfolio.R
import com.aura.portfolio.databinding.ItemBonusBinding
import com.aura.portfolio.databinding.ItemGradeBinding
import com.aura.portfolio.databinding.ItemProfitBinding
import com.aura.portfolio.databinding.ItemRefillBinding
import com.aura.portfolio.util.toPrice
import kotlin.math.roundToInt

class DashboardAdapter : ListAdapter<DashboardModel, BaseViewHolder<DashboardModel>>(object :
    DiffUtil.ItemCallback<DashboardModel>() {

    override fun areItemsTheSame(oldItem: DashboardModel, newItem: DashboardModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DashboardModel, newItem: DashboardModel): Boolean {
        return false
    }
}) {

    private val BONUS_TYPE = 31
    private val PROFIT_TYPE = 32
    private val GRADE_TYPE = 33
    private val REFILL_TYPE = 34

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<DashboardModel> {
        return when (viewType) {
            BONUS_TYPE -> BonusViewHolder(
                ItemBonusBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            PROFIT_TYPE -> ProfitViewHolder(
                ItemProfitBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            REFILL_TYPE -> RefillViewHolder(
                ItemRefillBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> GradeViewHolder(
                ItemGradeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is Bonus -> BONUS_TYPE
            is Grade -> GRADE_TYPE
            is Profit -> PROFIT_TYPE
            is Refill -> REFILL_TYPE
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DashboardModel>, position: Int) {
        holder.bind(currentList[position])
    }

    inner class RefillViewHolder(private val binding: ItemRefillBinding) :
        BaseViewHolder<DashboardModel>(binding) {
        override fun bind(item: DashboardModel) {
            item as Refill
            binding.tvRefill.text =
                binding.root.context.getString(R.string.x_dol, item.total?.toDouble()?.toPrice())
            binding.tvWithdrawn.text = binding.root.context.getString(
                R.string.x_dol,
                item.withdrawn?.toDouble()?.toPrice()
            )
            binding.tvRefillRub.text = binding.root.context.getString(
                R.string.x_rub,
                item.refillRub?.toDouble()?.toPrice()
            )
        }
    }

    inner class BonusViewHolder(private val binding: ItemBonusBinding) :
        BaseViewHolder<DashboardModel>(binding) {
        override fun bind(item: DashboardModel) {
            item as Bonus
            binding.tvBonus.text = item.totalRub?.toDouble()?.toPrice()
            binding.radioGroupCurrency.setOnCheckedChangeListener { radioGroup, i ->
                if (binding.radioRubBtn.isChecked)
                    binding.tvBonus.text = item.totalRub?.toDouble()?.toPrice()
                else
                    binding.tvBonus.text = item.totalUsd?.toDouble()?.toPrice()
            }
            binding.tvBonus.text = item.totalRub?.toDouble()?.toPrice()
            binding.tvTeam.text = item.team?.toString()
            binding.tvRefillRub.text = binding.root.context.getString(
                R.string.x_rub,
                item.refillRub?.toDouble()?.toPrice()
            )
        }
    }

    inner class ProfitViewHolder(private val binding: ItemProfitBinding) :
        BaseViewHolder<DashboardModel>(binding) {
        override fun bind(item: DashboardModel) {
            item as Profit
            item.raise?.let {
                if (it < 0) {
                    binding.tvPercent.setTextColor(
                        ResourcesCompat.getColor(
                            binding.root.context.resources,
                            R.color.red,
                            null
                        )
                    )
                    binding.tvPercent.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null,
                        null,
                        ResourcesCompat.getDrawable(
                            binding.root.context.resources,
                            R.drawable.ic_arrow_down,
                            null
                        ),
                        null
                    )
                }
            }
            binding.tvInvest.text =
                binding.root.context.getString(R.string.x_dol, item.invest?.toDouble()?.toPrice())
            binding.tvTotalPrice.text =
                binding.root.context.getString(R.string.x_dol, item.price?.toDouble()?.toPrice())
            binding.tvPercent.text =
                binding.root.context.getString(R.string.x_percent, item.raise?.toString())
            binding.tvProfit.text =
                binding.root.context.getString(R.string.x_dol, item.total?.toDouble()?.toPrice())
        }
    }

    inner class GradeViewHolder(private val binding: ItemGradeBinding) :
        BaseViewHolder<DashboardModel>(binding) {
        override fun bind(item: DashboardModel) {
            item as Grade
            item.raise?.let {
                if (it < 0) {
                    binding.tvPercent.setTextColor(
                        ResourcesCompat.getColor(
                            binding.root.context.resources,
                            R.color.red,
                            null
                        )
                    )
                    binding.tvPercent.setCompoundDrawables(
                        null,
                        null,
                        ResourcesCompat.getDrawable(
                            binding.root.context.resources,
                            R.drawable.ic_arrow_down,
                            null
                        ),
                        null
                    )
                }
            }
            binding.tvRubBalance.text = binding.root.context.getString(
                R.string.x_rub,
                item.balanceRub?.toDouble()?.toPrice()
            )
            binding.tvUsdBalance.text = binding.root.context.getString(
                R.string.x_dol,
                item.balanceUsd?.toDouble()?.toPrice()
            )
            binding.tvInStock.text =
                binding.root.context.getString(R.string.x_dol, item.stock?.toDouble()?.toPrice())
            binding.tvPercent.text =
                binding.root.context.getString(R.string.x_percent, item.raise?.toString())
            binding.tvGrade.text =
                binding.root.context.getString(R.string.x_dol, item.total?.toDouble()?.toPrice())
            item.balanceUsd?.let { usd ->
                item.balanceRub?.let { rub ->
                    item.stock?.let { stock ->
                        val total = usd + rub + stock
                        val stockView = View(binding.root.context)
                        stockView.apply {
                            this.background = ColorDrawable(ResourcesCompat.getColor(binding.root.context.resources, R.color.purple, null))
                            this.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, stock/total*100)
                        }
                        val usdView = View(binding.root.context)
                        usdView.apply {
                            this.background = ColorDrawable(ResourcesCompat.getColor(binding.root.context.resources, R.color.purple, null))
                            this.foreground = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.bg_left_rounded, null)
                            this.foregroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(binding.root.context.resources, R.color.green, null))
                            this.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, usd/total*100)
                        }
                        val rubView = View(binding.root.context)
                        rubView.apply {
                            this.background = ColorDrawable(ResourcesCompat.getColor(binding.root.context.resources, R.color.green, null))
                            this.foreground = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.bg_left_rounded, null)
                            this.foregroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(binding.root.context.resources, R.color.yellow, null))
                            this.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, rub/total*100)
                        }
                        binding.linearRateLinear.addView(stockView)
                        binding.linearRateLinear.addView(usdView)
                        binding.linearRateLinear.addView(rubView)
                    }
                }
            }
        }
    }
}

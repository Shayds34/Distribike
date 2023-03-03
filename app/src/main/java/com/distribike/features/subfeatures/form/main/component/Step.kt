package com.distribike.features.subfeatures.form.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Dimension.Companion
import androidx.constraintlayout.compose.atLeast
import com.distribike.ui.theme.GrayDark
import com.distribike.ui.theme.Green

/** A material step used in [Stepper]. The step can have a title and subtitle,
 * an icon within its circle, some content and a state that governs its
 * styling.
 *
 * also:
 *
 * @see [Stepper]
 * @see [Guidelines](https://material.io/archive/guidelines/components/steppers.html)
 */
@Immutable
data class Step(
    val title: String,
    val subtitle: String? = null,
    val activeIcon: ImageVector? = null,
    val isActive: Boolean = false,
    var state: MutableState<StepState> = mutableStateOf(StepState.TODO),
    val content: @Composable () -> Unit
)

enum class StepState {
    COMPLETE,
    ERROR,
    TODO,
    PASS
}

@Composable
fun StepUi(
    modifier: Modifier = Modifier,
    index: Int,
    step: Step,
    expanded: Boolean,
    isLastStep: Boolean,
    nextButton: @Composable (() -> Unit)? = null,
    passButton: @Composable (() -> Unit)? = null,
    previousButton: @Composable (() -> Unit)? = null,
) {

    ConstraintLayout(
        modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {

        val (stepIndicatorId, dividerId, titleId, contentId) = createRefs()

        val stepIndicatorConstraints = Modifier.constrainAs(stepIndicatorId) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
        when (step.state.value) {
            StepState.ERROR -> StepErrorIconIndicator(
                modifier = stepIndicatorConstraints,
                isActive = expanded
            )
            StepState.COMPLETE -> {
                StepIconIndicator(
                    modifier = stepIndicatorConstraints,
                    backgroundActiveColor = Green,
                    isActive = true,
                    icon = Rounded.Check
                )
            }
            StepState.PASS -> {
                StepIconIndicator(
                    modifier = stepIndicatorConstraints,
                    backgroundActiveColor = GrayDark,
                    isActive = true,
                    icon = Rounded.Close
                )
            }
            else ->
                if (step.activeIcon != null)
                    StepIconIndicator(
                        modifier = stepIndicatorConstraints,
                        isActive = expanded,
                        icon = step.activeIcon
                    )
                else
                    StepNumberIndicator(
                        modifier = stepIndicatorConstraints,
                        index,
                        isActive = expanded
                    )
        }

        StepTitle(
            modifier = Modifier
                .constrainAs(titleId) {
                    width = Companion.fillToConstraints
                    top.linkTo(stepIndicatorId.top)
                    bottom.linkTo(stepIndicatorId.bottom)
                    start.linkTo(stepIndicatorId.end, margin = 16.dp)
                    end.linkTo(parent.end)
                },
            title = step.title,
            subtitle = step.subtitle,
            active = expanded,
            isError = step.state.value == StepState.ERROR
        )

        if (!isLastStep) {
            StepDivider(
                color = Const.Grey400,
                modifier = Modifier
                    .constrainAs(dividerId) {
                        height = Dimension.fillToConstraints.atLeast(16.dp)
                        top.linkTo(stepIndicatorId.bottom, margin = 8.dp)
                        start.linkTo(stepIndicatorId.start)
                        end.linkTo(stepIndicatorId.end)
                        bottom.linkTo(contentId.bottom)
                    }
            )
        }

        StepContent(
            modifier = Modifier
                .constrainAs(contentId) {
                    width = Companion.fillToConstraints
                    top.linkTo(stepIndicatorId.bottom, margin = 8.dp)
                    start.linkTo(titleId.start)
                    end.linkTo(parent.end)
                },
            visible = expanded,
            nextButton = nextButton,
            passButton = passButton,
            previousButton = previousButton,
            step = step
        )
    }
}
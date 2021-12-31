package org.builds.nearlabs.presentation.ui.event

import org.builds.nearlabs.presentation.ui.navigation.NavTarget

sealed class NavEvent {
    object None : NavEvent()
    class NavigateUp(val inclusive: Boolean = false) : NavEvent()

    class Action(val target: NavTarget) : NavEvent()
    class ActionInclusive(val target: NavTarget, val inclusiveTarget: NavTarget) : NavEvent()
    class PopBackStack(val target: NavTarget, val inclusive: Boolean = false) : NavEvent()
}
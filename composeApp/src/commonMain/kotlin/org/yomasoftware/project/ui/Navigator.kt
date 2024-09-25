package org.yomasoftware.project.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.compose.ui.Modifier
import androidx.navigation.toRoute
import org.yomasoftware.project.ui.elements.pages.WeeklyWorkbook
import kotlinx.serialization.Serializable
import org.yomasoftware.project.ui.elements.pages.WeekAssignView
import org.yomasoftware.project.ui.elements.pages.WorkbookMonthsUI

@Composable
fun NavigatorApp(modifier: Modifier = Modifier,
                 isFirst:Boolean,
                 firstOnChange : (Boolean) -> Unit
                 ) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Months){
        composable<Months>{
            WorkbookMonthsUI(
                isFirst,
                firstOnChange = firstOnChange,
                navigationClick = {
                navController.navigate(Weeks(it.id,"",it.title))
            })
        }
        composable<Weeks> { backStackEntry ->
            val weeks = backStackEntry.toRoute<Weeks>()
         WeeklyWorkbook(idMonth = weeks.monthId, titleMonth = weeks.titleMonth,
             onItemClick = { navController.navigate(WeekAssignament(it.id,it.title)) },
             onBack = { navController.popBackStack()}
             )
        }
        composable<WeekAssignament> { backStackEntry ->
            val weeks = backStackEntry.toRoute<WeekAssignament>()
         WeekAssignView(
             idWeek = weeks.weekID,
             titleWeek = weeks.titleWeel,
             onBack = {navController.popBackStack()}
             )
        }
    }
}

@Serializable
object Months

@Serializable
data class Weeks(val monthId:Long,val linksMonth:String,val titleMonth:String  )

@Serializable
data class WeekAssignament(val weekID:Long, val titleWeel:String)



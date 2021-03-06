/** MonthlyBudgetPieChart
    Anderson, Franceschi
*/

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class MonthlyBudgetPieChart extends Application 
{
  private String [ ] categories 
    = { "Rent", "Food", "Phone", "Entertainment", "Utilities" }; 
  private double [ ] expenses 
    = { 800, 450, 72.50, 200, 90 }; 

  @Override 
  public void start( Stage stage ) 
  {
    // Create a PieChart.Data of montly expenses
    ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>( );
    for ( int i = 0; i < expenses.length; i++ )
      list.add( new PieChart.Data( categories[i], expenses[i] ) );  
    ObservableList<PieChart.Data> pieChartData 
      = FXCollections.observableList( list );
                
    // Create a PieChart with pieChartData
    PieChart chart = new PieChart( pieChartData );
    chart.setTitle( "Monthly Budget" );
            
    // Define the scene and stage
    Scene scene = new Scene( chart, 500, 400 );
    stage.setScene( scene );
    stage.setTitle( "Pie Chart for Monthly Expenses" );
    stage.show( );
  }
 
  public static void main( String [] args ) 
  {
    launch( args );
  }
}
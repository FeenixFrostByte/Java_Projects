/* AccountingController Class
*  Anderson, Franceschi
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

import javafx.animation.AnimationTimer;

public class AccountingController 
{
 private Transaction transaction;
 private double currentStudentSubTotal;
 
 private AccountingDrawing drawing;
  
 @FXML private BorderPane bp;
 
 private Pane pane;
 private Canvas canvas; 
 private GraphicsContext gc;
 
 private boolean result;
 
 public void initialize( )
 { 
   drawing = new AccountingDrawing( );
    
   pane = new Pane( );
   bp.setCenter( pane );
   canvas = new Canvas( Dimensions.APP_WIDTH - Dimensions.APP_PADDING, 
                        Dimensions.APP_HEIGHT - Dimensions.APP_PADDING );
   pane.getChildren( ).add( canvas );
   gc = canvas.getGraphicsContext2D( ); 
   
   drawing.balanceCheckBook( );
      
   if( ! drawing.isEmpty( ) )
   {
    transaction = drawing.getSubTotal( 0 ).getTransaction( );
    currentStudentSubTotal = drawing.getSubTotal( 0 ).getSubTotal( );
    transaction.setCurrentStudentSubTotal( currentStudentSubTotal );
    transaction.updateCurrentCorrectSubTotal( );
         
    startAnimation( );  
   }
 }

 public void startAnimation( ) 
 {
   ItemAnimationTimer timer = new ItemAnimationTimer( );
   timer.start( );
 }
      
 private class ItemAnimationTimer extends AnimationTimer 
 {
   @Override
   public void handle( long now ) 
   {         
     // update screen  
     gc.setFill( Color.rgb( 205, 205, 205 ) );  
     gc.fillRect( 0, 0, Dimensions.APP_WIDTH, Dimensions.APP_HEIGHT );       
     transaction.draw( gc, Color.rgb( 205, 205, 205 ) ); 
         
     if( transaction.done( ) )
     {
       stop( );
       drawing.incrementIndex( );
       // process next transaction
       if( ! ( drawing.done( ) ) )
       {
        double currentCorrectSubTotal = transaction.getCurrentCorrectSubTotal( );
        double amount = drawing.getSubTotal( drawing.getIndex( ) ).getAmount( );
        transaction = drawing.getSubTotal( drawing.getIndex( ) ).getTransaction( );
        currentStudentSubTotal = drawing.getSubTotal( drawing.getIndex( ) ).getSubTotal( );
        transaction.setCurrentStudentSubTotal( currentStudentSubTotal );
        transaction.setCurrentCorrectSubTotal( currentCorrectSubTotal );
        transaction.setAmount( amount ); 
        transaction.updateCurrentCorrectSubTotal( );
        transaction.resetX( );
        startAnimation( );
       }
      }
     
      Pause.wait( 0.05 );
      transaction.addToX( 2 );
      transaction.addToY( 2 );
   }
 }


}
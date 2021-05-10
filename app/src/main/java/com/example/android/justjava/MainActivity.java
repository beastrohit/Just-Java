/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if(quantity >= 10){
            quantity = 10;
        }else{
            quantity = quantity + 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 1) {
            quantity = 1;
        } else {
            quantity = quantity - 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_Cream_Checkbox);
        boolean haswhippedcream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_Checkbox);
        boolean haschocolate = chocolateCheckbox.isChecked();
        EditText editText = (EditText) findViewById(R.id.nametextfield);
        String name = editText.getText().toString();
        int price = calculatePrice(haswhippedcream, haschocolate);
        String priceMessage = createOrderSummary(price, haswhippedcream, haschocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for coffee.");
        startActivity(intent);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private int calculatePrice(boolean haswhippedcream, boolean haschocolate) {
        int baseprice = 5;
        if(haswhippedcream){
            baseprice = baseprice + 1;
        }
        if(haschocolate){
            baseprice = baseprice + 2;
        }
        return quantity * baseprice;
    }

    private String createOrderSummary(int price, boolean haswhippedcream, boolean haschocolate, String name) {
        String priceMessage = "Name : " + name;
        priceMessage = priceMessage + "\nHas Whipped Cream = " + haswhippedcream;
        priceMessage = priceMessage + "\nHas Chocolate = " + haschocolate;
        priceMessage = priceMessage + "\nQuantity : " + quantity;
        priceMessage = priceMessage + "\nTotal : " + price + "\nThank You!";
        return priceMessage;
    }
}
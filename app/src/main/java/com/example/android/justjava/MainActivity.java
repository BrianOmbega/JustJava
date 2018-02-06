
package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    int price = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name_edittext);
        String name = nameEditText.getText().toString();

        price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);

        displayMessage(priceMessage);



    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        // limit number of cups to 100
        if (quantity == 100) {
         // Alert user to limit them from ordering more than 100 cups
            Toast.makeText(this, "You cannot have more than a 100 cups of coffee",
                    Toast.LENGTH_LONG).show();
         // Exit method
            return;

        }

        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
        // alert user on minimum cups of coffee
            Toast.makeText(this, "You cannot have less than 1 coffee",
                    Toast.LENGTH_SHORT).show();
            return;

        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView ordersummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        ordersummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *@return the total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        // Price of coffee plus both whipped cream
        if (addWhippedCream) {
            basePrice += 1;
        }
        // Price of coffee plus chocolate
        if (addChocolate) {
            basePrice += 2;
        }

        return quantity * basePrice;
    }
    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @param name            is name of the customer
     * @return text summary
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate,
                                      String name){
        String orderSummary = "Name: " + name;
        orderSummary += "\nHas Whipped cream?" + addWhippedCream;
        orderSummary += "\nHas Chocolate?" + addChocolate;
        orderSummary = orderSummary + "\nQuantity: " + quantity;
        orderSummary = orderSummary + "\nTotal: " + price;
        orderSummary = orderSummary + "\nThank You!";

        return orderSummary;

    }
}

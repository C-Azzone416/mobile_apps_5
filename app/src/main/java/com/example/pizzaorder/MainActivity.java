package com.example.pizzaorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.Map;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {
    ToggleButton roundPizzaBtn;
    ImageView mainImg;
    ToggleButton squarePizzaBtn;

    CheckBox pepperoniCheckBox;
    CheckBox mushroomCheckBox;
    CheckBox veggiesCheckBox;
    CheckBox anchoviesCheckBox;

    RadioGroup cheeseChoice;

    ChipGroup ingredientsChips;

    Chip cheeseChip;


    private static final int PHONE_LENGTH = 10;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roundPizzaBtn = findViewById(R.id.round_pizzabtn);
        squarePizzaBtn = findViewById(R.id.square_pizzabtn);
        mainImg = findViewById(R.id.mainImage);
        submitBtn = findViewById(R.id.submit_btn);

        pepperoniCheckBox = findViewById(R.id.pepperoni_check);
        mushroomCheckBox = findViewById(R.id.mushroom_check);
        veggiesCheckBox = findViewById(R.id.veggies_check);
        anchoviesCheckBox = findViewById(R.id.anchovies_check);

        cheeseChoice = findViewById(R.id.cheese_btns);

        ingredientsChips = findViewById(R.id.topping_choice_chip_group);

        roundPizzaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImg.setImageDrawable(getDrawable(R.drawable.ic_round_pizza));
                squarePizzaBtn.setChecked(false);

                if (!squarePizzaBtn.isChecked() && !roundPizzaBtn.isChecked()) {
                    mainImg.setImageDrawable(getDrawable(R.drawable.ic_not_selected_pizza));
                }

            }

        });

        squarePizzaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainImg.setImageDrawable(getDrawable(R.drawable.ic_squared_pizza));
                roundPizzaBtn.setChecked(false);

                if (!squarePizzaBtn.isChecked() && !roundPizzaBtn.isChecked()) {
                    mainImg.setImageDrawable(getDrawable(R.drawable.ic_not_selected_pizza));
                }
            }

        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            TextInputEditText phoneEditText = findViewById(R.id.phone_entry);
            TextInputLayout phoneInput = findViewById(R.id.phone_input);

            @Override
            public void onClick(View v) {
                if (phoneEditText.length() != PHONE_LENGTH) {
                    phoneInput.setError(getResources().getString(R.string.error_text));

                } else {
                    phoneInput.setErrorEnabled(false);
                }
            }
        });

        pepperoniCheckBox.setOnCheckedChangeListener(this);
        veggiesCheckBox.setOnCheckedChangeListener(this);
        anchoviesCheckBox.setOnCheckedChangeListener(this);
        mushroomCheckBox.setOnCheckedChangeListener(this);
        cheeseChip = new Chip(this, null, R.attr.chipStyle);
        cheeseChoice.setOnCheckedChangeListener(this);


    }

    Map<String, Chip> ingredientsMap = new HashMap<String, Chip>();


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        String ingredient = compoundButton.getText().toString();
        if (isChecked) {
            addChipToChipGroup(ingredient, ingredientsChips);
        } else {
            removeChipFromGroup(ingredient, ingredientsChips);
        }
    }

    private void addChipToChipGroup(String ingredient, ChipGroup ingredientsChips) {
        Chip chip = new Chip(this, null, R.attr.chipStyle);
        chip.setText(ingredient);
        ingredientsChips.addView(chip);
        ingredientsMap.put(ingredient, chip);
    }

    private void removeChipFromGroup(String ingredient, ChipGroup ingredientsChips){
        Chip chip = ingredientsMap.get(ingredient);
        ingredientsChips.removeView(chip);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        String ingredient;

        if (cheeseChip != null) {
            ingredientsChips.removeView(cheeseChip);
        }

        switch (checkedId) {

            case R.id.reg_cheesebtn:
                ingredient = getResources().getString(R.string.reg_cheese);
                cheeseChip.setText(ingredient);
                ingredientsChips.addView(cheeseChip);
                break;
            case R.id.double_cheesebtn:
                ingredient = getResources().getString(R.string.double_cheese);
                cheeseChip.setText(ingredient);
                ingredientsChips.addView(cheeseChip);
                break;
            case R.id.no_cheeseBtn:
            default:
                ingredientsChips.removeView(cheeseChip);
                break;
        }
    }
}



package com.example.myapplication;

import static android.view.View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CalculatorFragment extends Fragment {
    private final String TAG = "TAG_CalculatorFragment";
    ArrayList<View> numBtns = new ArrayList<>();
    ArrayList<View> opBtns = new ArrayList<>();
    private Button bt_eq, bt_fact, bt_ac;
    private EditText editText;
    private boolean clean;
    String op = "";
    double result = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleButton();
    }

    private void findViews(View view) {
        view.findViewsWithText(numBtns, "number button", FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        view.findViewsWithText(opBtns, "operator button", FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        bt_eq = view.findViewById(R.id.bt_eq);
        bt_fact = view.findViewById(R.id.bt_fact);
        bt_ac = view.findViewById(R.id.bt_ac);
        editText = view.findViewById(R.id.et_num);
    }

    private void handleButton() {
        for (View view : numBtns) {
            view.setOnClickListener(onNumBtnClick);
        }
        for (View view : opBtns) {
            view.setOnClickListener(onOpBtnClick);
        }
        bt_ac.setOnClickListener(onAcBtnClick);
        bt_eq.setOnClickListener(onEqualBtnClick);
        bt_fact.setOnClickListener(onFactorialBtnClick);
    }


    private final View.OnClickListener onNumBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String input = editText.getText().toString();
            if (clean) {
                clean = false;
                input = "";
                editText.setText("");
            }
            editText.setText(input + ((Button) view).getText() + "");
        }
    };

    private final View.OnClickListener onOpBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String input = editText.getText().toString();
            if (clean) {
                clean = false;
                editText.setText("");
            }
            op = ((Button) view).getText() + "";
            editText.setText(input + op);
        }
    };

    private final View.OnClickListener onAcBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String input = editText.getText().toString();
            if (clean) {
                clean = false;
                editText.setText("");
            } else if (!input.equals("")) {
                editText.setText("");
            }
        }
    };

    private final View.OnClickListener onEqualBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getResult();
            op = "";
            result = 0;
        }
    };

    private final View.OnClickListener onFactorialBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String input = editText.getText().toString();
            if (clean) {
                clean = false;
                editText.setText("");
            }
            op = "!";
            editText.setText(input + op);
        }
    };


    private void getResult() {
        String formulaStr = editText.getText().toString();
        if (formulaStr.equals("")) {
            return;
        }
        if (op.equals("")) {
            return;
        }

        clean = !clean;
        if (!clean) return;

        String numStr1 = formulaStr.substring(0, formulaStr.indexOf(op));
        String numStr2 = formulaStr.substring(formulaStr.indexOf(op) + 1);
        double num1 = numStr1.equals("") ? 0 : Double.parseDouble(numStr1);
        double num2 = numStr2.equals("") ? 0 : Double.parseDouble(numStr2);

        if (op.equals("+")) {
            result = num1 + num2;
        } else if (op.equals("-")) {
            result = num1 - num2;
        } else if (op.equals("*")) {
            result = num1 * num2;
        } else if (op.equals("/")) {
            result = num1 / num2;
        }

        if (result % 1 == 0) {
            int r = (int) result;
            editText.setText(r + "");
        } else {
            editText.setText(result + "");
        }

        if (formulaStr.equals(numStr1 + "!")) {
            long factResult = 1;
            int factTimes = (int) num1;
            for (int i = 1; i <= factTimes; i++) {
                factResult *= i;
            }
            editText.setText(factResult + "");
        } else if (op.equals("!")) {
            editText.setText("");
        }
    }
}
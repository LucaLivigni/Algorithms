package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.math.BigInteger;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Controller {

    @FXML
    private TextField gValue, pValue, secretValueA, secretValueB;
    @FXML
    private Label kA, kB, simmetricKey, formulaA1, formulaA2, formulaA3, formulaB1, formulaB2, formulaB3, timer;





    public void calculate(){

        long startTime = Instant.now().toEpochMilli();

        try {
            BigInteger g = new BigInteger(gValue.getText());
            BigInteger p = new BigInteger(pValue.getText());
            BigInteger a = new BigInteger(secretValueA.getText());
            BigInteger b = new BigInteger(secretValueB.getText());

            BigInteger ab = a.multiply(b);
            BigInteger k = calcoloK(g, ab, p);


            kA.setText(String.valueOf(calcoloK(g, a, p)));
            formulaA1.setText("= "+ g + "^(" + a + "*" + b + ") mod " + p + " =");
            formulaA2.setText("= "+ g + "^" + ab + " mod " + p + " ");
            formulaA3.setText("= " + k);

            kB.setText(String.valueOf(calcoloK(g, b, p)));
            formulaB1.setText("= "+ g + "^(" + b + "*" + a + ") mod " + p + " =");
            formulaB2.setText("= "+ g + "^" + ab + " mod " + p + " ");
            formulaB3.setText("= " + k);

            simmetricKey.setText(String.valueOf(k));
        }catch (NumberFormatException e) {
            simmetricKey.setText("All fields must contain an integer");
        }

        long endTime = Instant.now().toEpochMilli();
        long timeElapsed = endTime - startTime;
        timer.setText("milliseconds: " + timeElapsed);
    }

    private BigInteger calcoloK(BigInteger g, BigInteger ab, BigInteger p) {
        BigInteger r;
        BigInteger two = new BigInteger("2");
        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");
        if(ab.mod(two).compareTo(zero) == 0){
            BigInteger n = new BigInteger(String.valueOf(ab.divide(two)));
            r = g.pow(2).mod(p).multiply(n).mod(p);
        } else {
            BigInteger n = new BigInteger(String.valueOf((two.subtract(one))));
            r = g.pow(2).mod(p).multiply(n).mod(p);
        }
        return r;
    }
}

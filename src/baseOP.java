/*
Funzioni:

somma(numero1, numero2)     --> ritorna un carattere [unità della somma tra due cifre (non considera il riporto es 2+8=0)]
rip_somma(numero1, numero2) --> ritorna un carattere [0 | 1]
sottr(numero1, numero2)     --> ritorna un carattere [unità della differenza tra due cifre (non considera il riporto es 2-8=4)]
req_sottr(numero1, numero2) --> ritorna un booleano  [true:la sottrazione richiede una decina (aka num1 < num2)]
moltp(numero1, numero2)     --> ritorna un carattere [unità della moltiplicazione tra 2 cifre (non considera il riporto es 8x4=2)]
rip_moltp(numero1, numero2) --> ritorna un carattere [riporto di una moltiplicazione tra due cifre (es 8x4=3)]

confronto(numero1, numero2, boolean conf, boolean equal) --> ritorna un booleano:
                                                                                  conf=true  equal=false [ true se numero1 > numero2 ]
                                                                                  conf=true  equal=true  [ true se numero1 >= numero2 ]
                                                                                  conf=false equal=false [ true se numero1 < numero2 ]
                                                                                  conf=false equal=true  [ true se numero1 <= numero2 ]

seleziona(numero1, numero2, boolean conf) ---> ritorna un carattere: il maggiore se conf=true, il minore se conf=false


*/

public class baseOP
{
    public static char somma(char add1, char add2)
    {
             if(add1 == '0') return add2;
        else if(add2 == '0') return add1;
        else if(add1 == '1')
            switch (add2) {
                case '1': return '2';
                case '2': return '3';
                case '3': return '4';
                case '4': return '5';
                case '5': return '6';
                case '6': return '7';
                case '7': return '8';
                case '8': return '9';
                case '9': return '0';
            }
        else if(add1 == '2')
            switch (add2) {
                case '1': return '3';
                case '2': return '4';
                case '3': return '5';
                case '4': return '6';
                case '5': return '7';
                case '6': return '8';
                case '7': return '9';
                case '8': return '0';
                case '9': return '1';
            }
        else if(add1 == '3')
            switch (add2) {
                case '1': return '4';
                case '2': return '5';
                case '3': return '6';
                case '4': return '7';
                case '5': return '8';
                case '6': return '9';
                case '7': return '0';
                case '8': return '1';
                case '9': return '2';
            }
        else if(add1 == '4')
            switch (add2) {
                case '1': return '5';
                case '2': return '6';
                case '3': return '7';
                case '4': return '8';
                case '5': return '9';
                case '6': return '0';
                case '7': return '1';
                case '8': return '2';
                case '9': return '3';
            }
        else if(add1 == '5')
            switch (add2) {
                case '1': return '6';
                case '2': return '7';
                case '3': return '8';
                case '4': return '9';
                case '5': return '0';
                case '6': return '1';
                case '7': return '2';
                case '8': return '3';
                case '9': return '4';
            }
        else if(add1 == '6')
            switch (add2) {
                case '1': return '7';
                case '2': return '8';
                case '3': return '9';
                case '4': return '0';
                case '5': return '1';
                case '6': return '2';
                case '7': return '3';
                case '8': return '4';
                case '9': return '5';
            }
        else if(add1 == '7')
            switch (add2) {
                case '1': return '8';
                case '2': return '9';
                case '3': return '0';
                case '4': return '1';
                case '5': return '2';
                case '6': return '3';
                case '7': return '4';
                case '8': return '5';
                case '9': return '6';
            }
        else if(add1 == '8')
            switch (add2) {
                case '1': return '9';
                case '2': return '0';
                case '3': return '1';
                case '4': return '2';
                case '5': return '3';
                case '6': return '4';
                case '7': return '5';
                case '8': return '6';
                case '9': return '7';
            }
        else if(add1 == '9')
            switch (add2) {
                case '1': return '0';
                case '2': return '1';
                case '3': return '2';
                case '4': return '3';
                case '5': return '4';
                case '6': return '5';
                case '7': return '6';
                case '8': return '7';
                case '9': return '8';
            }
        return 'X';
    }

    public static char rip_somma(char add1, char add2)
    {
             if (add1 == '0' || add2 == '0') return '0';
        else if (add1 == '9' || add2 == '9') return '1';
        else if (add1 == '1' || add2 == '1') return '0';
        else if (add1 == '8' || add2 == '8') return '1';
        else if (add1 == '2' || add2 == '2') return '0';
        else if (add1 == '7' || add2 == '7') return '1';
        else if (add1 == '3' || add2 == '3') return '0';
        else if (add1 == '6' || add2 == '6') return '1';
        else if (add1 == '4' || add2 == '4') return '0';
        else if (add1 == '5' || add2 == '5') return '1';

        return 'X';
    }

    public static char sottr(char add1, char add2)
    {
             if (add1 == add2) return '0';
        else if (add2 == '0')  return add1;
        else if (add1 == '0')
            switch (add2) {
                case '1': return '9';
                case '2': return '8';
                case '3': return '7';
                case '4': return '6';
                case '5': return '5';
                case '6': return '4';
                case '7': return '3';
                case '8': return '2';
                case '9': return '1';
            }
        else if(add1 == '1')
            switch (add2) {
                case '2': return '9';
                case '3': return '8';
                case '4': return '7';
                case '5': return '6';
                case '6': return '5';
                case '7': return '4';
                case '8': return '3';
                case '9': return '2';
            }
        else if(add1 == '2')
            switch (add2) {
                case '1': return '1';
                case '3': return '9';
                case '4': return '8';
                case '5': return '7';
                case '6': return '6';
                case '7': return '5';
                case '8': return '4';
                case '9': return '3';
            }
        else if(add1 == '3')
            switch (add2) {
                case '1': return '2';
                case '2': return '1';
                case '4': return '9';
                case '5': return '8';
                case '6': return '7';
                case '7': return '6';
                case '8': return '5';
                case '9': return '4';
            }
        else if(add1 == '4')
            switch (add2) {
                case '1': return '3';
                case '2': return '2';
                case '3': return '1';
                case '5': return '9';
                case '6': return '8';
                case '7': return '7';
                case '8': return '6';
                case '9': return '5';
            }
        else if(add1 == '5')
            switch (add2) {
                case '1': return '4';
                case '2': return '3';
                case '3': return '2';
                case '4': return '1';
                case '6': return '9';
                case '7': return '8';
                case '8': return '7';
                case '9': return '6';
            }
        else if(add1 == '6')
            switch (add2) {
                case '1': return '5';
                case '2': return '4';
                case '3': return '3';
                case '4': return '2';
                case '5': return '1';
                case '7': return '9';
                case '8': return '8';
                case '9': return '7';
            }
        else if(add1 == '7')
            switch (add2) {
                case '1': return '6';
                case '2': return '5';
                case '3': return '4';
                case '4': return '3';
                case '5': return '2';
                case '6': return '1';
                case '8': return '9';
                case '9': return '8';
            }
        else if(add1 == '8')
            switch (add2) {
                case '1': return '7';
                case '2': return '6';
                case '3': return '5';
                case '4': return '4';
                case '5': return '3';
                case '6': return '2';
                case '7': return '1';
                case '9': return '9';
            }
        else if(add1 == '9')
            switch (add2) {
                case '1': return '8';
                case '2': return '7';
                case '3': return '6';
                case '4': return '5';
                case '5': return '4';
                case '6': return '3';
                case '7': return '2';
                case '8': return '1';
            }
        return 'X';
    }

    public static boolean req_sottr(char add1, char add2)
    {
             if (add1 == add2) return false;
        else if (add2 == '0')  return false;
        else if (add1 == '0')  return true;
        else if (add1 == '9')  return false;
        else if (add2 == '9')  return true;
        else if (add1 == '8')  return false;
        else if (add2 == '8')  return true;
        else if (add1 == '7')  return false;
        else if (add2 == '7')  return true;
        else if (add1 == '6')  return false;
        else if (add2 == '6')  return true;
        else if (add1 == '5')  return false;
        else if (add2 == '5')  return true;
        else if (add1 == '4')  return false;
        else if (add2 == '4')  return true;
        else if (add1 == '3')  return false;
        else if (add2 == '3')  return true;
        else if (add1 == '2')  return false;
        else if (add2 == '2')  return true;

        return false;
    }

    public static char moltp (char fact1, char fact2)
    {
             if (fact1 == '0' || fact2 == '0') return '0';
        else if (fact1 == '1') return fact2;
        else if (fact2 == '1') return fact1;
        else if (fact1 == '+' && fact2 == '+') return '+';
        else if (fact1 == '+' && fact2 == '-') return '-';
        else if (fact1 == '-' && fact2 == '+') return '-';
        else if (fact1 == '-' && fact2 == '-') return '+';
        else if (fact1 == '2')
            switch (fact2) {
                case '2': return '4';
                case '3': return '6';
                case '4': return '8';
                case '5': return '0';
                case '6': return '2';
                case '7': return '4';
                case '8': return '6';
                case '9': return '8';
            }
        else if (fact1 == '3')
            switch (fact2) {
                case '2': return '6';
                case '3': return '9';
                case '4': return '2';
                case '5': return '5';
                case '6': return '8';
                case '7': return '1';
                case '8': return '4';
                case '9': return '7';
            }
        else if (fact1 == '4')
            switch (fact2) {
                case '2': return '8';
                case '3': return '2';
                case '4': return '6';
                case '5': return '0';
                case '6': return '4';
                case '7': return '8';
                case '8': return '2';
                case '9': return '6';
            }
        else if (fact1 == '5')
            switch (fact2) {
                case '2': return '0';
                case '3': return '5';
                case '4': return '0';
                case '5': return '5';
                case '6': return '0';
                case '7': return '5';
                case '8': return '0';
                case '9': return '5';
            }
        else if (fact1 == '6')
            switch (fact2) {
                case '2': return '2';
                case '3': return '8';
                case '4': return '4';
                case '5': return '0';
                case '6': return '6';
                case '7': return '2';
                case '8': return '8';
                case '9': return '4';
            }
        else if (fact1 == '7')
            switch (fact2) {
                case '2': return '4';
                case '3': return '1';
                case '4': return '8';
                case '5': return '5';
                case '6': return '2';
                case '7': return '9';
                case '8': return '6';
                case '9': return '3';
            }
        else if (fact1 == '8')
            switch (fact2) {
                case '2': return '6';
                case '3': return '4';
                case '4': return '2';
                case '5': return '0';
                case '6': return '8';
                case '7': return '6';
                case '8': return '4';
                case '9': return '2';
            }
        else if (fact1 == '9')
            switch (fact2) {
                case '2': return '8';
                case '3': return '7';
                case '4': return '6';
                case '5': return '5';
                case '6': return '4';
                case '7': return '3';
                case '8': return '2';
                case '9': return '1';
            }
        return 'X';
    }

    public static char rip_moltp (char fact1, char fact2)
    {
             if(fact1 == '0' || fact2 == '0') return '0';
        else if(fact1 == '1' || fact2 == '1') return '0';
        else if (fact1 == '2')
            switch (fact2) {
                case '2': return '0';
                case '3': return '0';
                case '4': return '0';
                case '5': return '1';
                case '6': return '1';
                case '7': return '1';
                case '8': return '1';
                case '9': return '1';
            }
        else if (fact1 == '3')
            switch (fact2) {
                case '2': return '0';
                case '3': return '0';
                case '4': return '1';
                case '5': return '1';
                case '6': return '1';
                case '7': return '2';
                case '8': return '2';
                case '9': return '2';
            }
        else if (fact1 == '4')
            switch (fact2) {
                case '2': return '0';
                case '3': return '1';
                case '4': return '1';
                case '5': return '2';
                case '6': return '2';
                case '7': return '2';
                case '8': return '3';
                case '9': return '3';
            }
        else if (fact1 == '5')
            switch (fact2) {
                case '2': return '1';
                case '3': return '1';
                case '4': return '2';
                case '5': return '2';
                case '6': return '3';
                case '7': return '3';
                case '8': return '4';
                case '9': return '4';
            }
        else if (fact1 == '6')
            switch (fact2) {
                case '2': return '1';
                case '3': return '1';
                case '4': return '2';
                case '5': return '3';
                case '6': return '3';
                case '7': return '4';
                case '8': return '4';
                case '9': return '5';
            }
        else if (fact1 == '7')
            switch (fact2) {
                case '2': return '1';
                case '3': return '2';
                case '4': return '2';
                case '5': return '3';
                case '6': return '4';
                case '7': return '4';
                case '8': return '5';
                case '9': return '6';
            }
        else if (fact1 == '8')
            switch (fact2) {
                case '2': return '1';
                case '3': return '2';
                case '4': return '3';
                case '5': return '4';
                case '6': return '4';
                case '7': return '5';
                case '8': return '6';
                case '9': return '7';
            }
        else if (fact1 == '9')
            switch (fact2) {
                case '2': return '1';
                case '3': return '2';
                case '4': return '3';
                case '5': return '4';
                case '6': return '5';
                case '7': return '6';
                case '8': return '7';
                case '9': return '8';
            }
        return 'X';

    }

    public static boolean confronto(char num1, char num2, boolean conf, boolean equal)
    {
             if(num1 == num2 && equal == true) return true;
        else if (req_sottr(num1, num2) && conf == false) return true;
        else if (req_sottr(num2, num1) && conf == true) return true;
        else return false;
    }

    public static char seleziona(char num1, char num2, boolean conf)
    {
        if(confronto(num1, num2, true, true) && conf == true) return num1;
        else return num2;
    }

    public static boolean isDigit(char c) {
        if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' ||
           c == '5' || c == '6' || c == '7' || c == '8' || c == '9') return true;
        else return false;
    }
}

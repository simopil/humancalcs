public class complexOP{

    // comparison between two numero-object
    public static boolean compare(numero num1, char op, char eq, numero num2, boolean absolute)
    {
        if(!absolute) {
            if(num1.getSign() == '+' && num2.getSign() == '-') {
                if(op == '>') return true;
                else          return false;
            }
            if(num1.getSign() == '-' && num2.getSign() == '+') {
                if(op == '<') return true;
                else          return false;
            }
        }
        //checking zeroes
        if(num1.pre_comma() == 1 && num1.post_comma() == 0 && num1.getChar(0) == '0')
        {
            if(num2.pre_comma() == 1 && num2.post_comma() == 0 && num2.getChar(0) == '0')
                if(op == '=' || eq == '=') return true;
                else return false;
            else
                if(op == '<') return true;
                else return false;
        }
        if(num2.pre_comma() == 1 && num2.post_comma() == 0 && num2.getChar(0) == '0')
        {
            if(op == '>') return true;
            else return false;
        }
        //if num1 has more digits on the left
        if (num1.pre_comma() > num2.pre_comma())
        {
            // compara i segni (se son due meno si inverte il risultato)
            if (!absolute && num1.getSign() == '-') {
                if(op == '<') return true;
                else          return false;
            }
            if(op == '>') return true;
            else          return false;
        }
        //if num2 has more digits on the left
        if (num1.pre_comma() < num2.pre_comma())
        {
            // compara i segni (se son due meno si inverte il risultato)
            if (!absolute && num1.getSign() == '-') {
                if(op == '>') return true;
                else          return false;
            }
            if(op == '<') return true;
            else          return false;
        }
        //if both have same size on the left, performs digit-by-digit comparison of non-decimal part
        for (int i = num1.pre_comma()-1; i >= 0; i--)
        {
            if (num1.getChar(i) != num2.getChar(i)) {
                if (baseOP.confronto(num1.getChar(i), num2.getChar(i), ((num1.getSign() == '-' && absolute) || num1.getSign() == '+'), false))
                {
                    if(op == '>') return true;
                    else          return false;
                } else {
                    if(op == '<') return true;
                    else          return false;
                }
            }
        }
        //if both are equals on the left, check digitwise on the right of the comma (if present)
        int tries = 0;
        if (num1.post_comma() > num2.post_comma()) tries = num1.post_comma();
        else tries = num2.post_comma();
        for(int i = -1; i >= -tries; i--)
        {
            if (num1.getChar(i) != num2.getChar(i)) {
                if (baseOP.confronto(num1.getChar(i), num2.getChar(i), ((num1.getSign() == '-' && absolute) || num1.getSign() == '+'), false))
                {
                    if(op == '>') return true;
                    else          return false;
                } else {
                    if(op == '<') return true;
                    else          return false;
                }
            }
        }
        //assumed that only exception is EQUALITY
        if(op == '=' || eq == '=') return true;
        else return false;
    }

    public static numero sum(numero add1, numero add2)
    {

        int start = -numero.getPrecisest(add1, add2).post_comma();
        int stop  =  numero.getLongestIntPart(add1, add2).pre_comma();
        numero res = new numero('+', stop-start+1);
        res.factor10(start);

        for (int i = start; i < stop; i++)
        {
            if (baseOP.rip_somma(res.getChar(i), add1.getChar(i)) == '1')
                res.putChar(baseOP.rip_somma(res.getChar(i),add1.getChar(i)), i+1);

            res.putChar(baseOP.somma(res.getChar(i),add1.getChar(i)), i);

            if (baseOP.rip_somma(res.getChar(i),add2.getChar(i)) == '1')
            {
                res.putChar(baseOP.somma(res.getChar(i+1), baseOP.rip_somma(res.getChar(i),add2.getChar(i))), i+1);
            }

            res.putChar(baseOP.somma(res.getChar(i),add2.getChar(i)), i);
        }

        res.update();
        return res;
    }

    public static numero subtraction(numero add1, numero add2)
    {
        numero res, mod_bigger, mod_little;
        int res_pre_comma;
        int res_post_comma;
        if(add1.pre_comma() >= add2.pre_comma()) res_pre_comma = add1.pre_comma();
        else res_pre_comma = add2.pre_comma();

        if(add1.post_comma() >= add2.post_comma()) res_post_comma = add1.post_comma();
        else res_post_comma = add2.post_comma();

        //make bigger always on top in subtraction
        if(compare(add1, '>', '=', add2, true)) {
            res = new numero('+', res_pre_comma+res_post_comma);
            mod_bigger = add1;
            mod_little = add2;
        } else {
            res = new numero('-', res_pre_comma+res_post_comma);
            mod_bigger = add2;
            mod_little = add1;
        }

        // dividing result by the precision in order to change comma position
        res.factor10(-res_post_comma);
        //cycle
        char aux;
        for(int i = -res_post_comma; i < res_pre_comma; i++) {

            aux = mod_bigger.getChar(i);
            if(res.getChar(i) == '1') {
                if(baseOP.req_sottr(mod_bigger.getChar(i), '1')) {
                    res.putChar('1', i+1);
                    aux = '9';
                } else {
                    aux = baseOP.sottr(mod_bigger.getChar(i), '1');
                }
            }
            if(baseOP.req_sottr(aux, mod_little.getChar(i)))
                res.putChar('1', i+1);

            res.putChar(baseOP.sottr(aux, mod_little.getChar(i)), i);
        }
        res.update();
        return res;
    }

    public static numero algebraicSum(numero num1, numero num2, char operand)
    {
        if (num1.getSign() == num2.getSign())
        {
            if (operand == '+')
            {
                numero res = sum(num1, num2);
                res.setSign(num1.getSign());
                return res;
            }
            else
            {
                if (num1.getSign() == '+')   return subtraction(num1, num2);
                else                         return subtraction(num2, num1);
            }
        }
        else if (num1.getSign() == '+')
        {
            if (operand == '+')   return subtraction(num1, num2);
            else                  return sum(num1, num2);
        }
        else
        {
            if (operand == '+')
                return subtraction(num2, num1);
            else
            {
                numero res = sum(num1, num2);
                res.setSign('-');
                return res;
            }
        }
    }

    public static numero mult(numero fact1, numero fact2)
    {
        //checking zeroes
        if(compare(fact1, '=', '=', numero.ZERO, true)) return numero.getZero();
        if(compare(fact2, '=', '=', numero.ZERO, true)) return numero.getZero();

        int len1 = fact1.post_comma()+fact1.pre_comma();
        int len2 = fact2.post_comma()+fact2.pre_comma();
        numero res = new numero('+', len1 + len2);
        int cnt1 = 0;
        for (int i = -fact1.post_comma(); i < fact1.pre_comma(); i++)
        {
            numero aux_row = new numero('+', len2+1);
            int cnt2 = 0;
            for (int j = -fact2.post_comma(); j < fact2.pre_comma(); j++)
            {
                numero aux_op = new numero('+', 2);

                aux_op.putChar(baseOP.moltp(fact1.getChar(i), fact2.getChar(j)), 0);
                aux_op.putChar(baseOP.rip_moltp(fact1.getChar(i), fact2.getChar(j)), 1);
                aux_op.factor10(cnt2);
                cnt2++;
                aux_row = sum(aux_row, aux_op);
            }
            aux_row.factor10(cnt1);
            cnt1++;
            res = sum(res, aux_row);
        }
        res.setSign(baseOP.moltp(fact1.getSign(), fact2.getSign()));
        res.factor10(-(fact1.post_comma() + fact2.post_comma()));

        res.update();

        return res;
    }

}

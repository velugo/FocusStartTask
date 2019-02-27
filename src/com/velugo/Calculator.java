package com.velugo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

class Calculator {
    String evaluate(String statement){
        if (statement == null || statement.isEmpty() || statement.contains(",") || statement.contains(" ")) {
            return "Синтаксическая ошибка выражения!";
        } else {
            String sIn = "";
            ArrayList<Character> list = new ArrayList<Character>();
            for (int i = 0; i < statement.length(); i++) {
                char c = statement.charAt(i);
                if (i == 0) {
                    list.add('0');
                    list.add(c);
                } else if (c == '('){
                    list.add(c);
                    list.add('0');
                } else {
                    list.add(c);
                }
            }
            for (char c : list) {
                sIn += c;
            }
            sIn = opn(sIn);
            String out;
            try {
                out = String.valueOf(calculate(sIn));
                return (out.contains(".0")) ? out.substring(0, out.length() - 2) : out;
            } catch (Exception e) {
                return "Логическая ошибка выражения!";
            }
        }
    }

    private static String opn(String sIn) {
        StringBuilder sbStack = new StringBuilder(""), sbOut = new StringBuilder("");
        char cIn, cTmp;

        for (int i = 0; i < sIn.length(); i++) {
            cIn = sIn.charAt(i);
            if (isOp(cIn)) {
                while (sbStack.length() > 0) {
                    cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                    if (isOp(cTmp) && (opPrior(cIn) <= opPrior(cTmp))) {
                        sbOut.append(" ").append(cTmp).append(" ");
                        sbStack.setLength(sbStack.length()-1);
                    } else {
                        sbOut.append(" ");
                        break;
                    }
                }
                sbOut.append(" ");
                sbStack.append(cIn);
            } else if ('(' == cIn) {
                sbStack.append(cIn);

            } else if (')' == cIn) {
                cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                while ('(' != cTmp) {
                    if (sbStack.length() < 1) {
                        return null;
                    }
                    sbOut.append(" ").append(cTmp);
                    sbStack.setLength(sbStack.length()-1);
                    cTmp = sbStack.substring(sbStack.length()-1).charAt(0);
                }
                sbStack.setLength(sbStack.length()-1);
            } else {
                sbOut.append(cIn);
            }
        }

        while (sbStack.length() > 0) {
            sbOut.append(" ").append(sbStack.substring(sbStack.length()-1));
            sbStack.setLength(sbStack.length()-1);
        }

        return  sbOut.toString();
    }

    private static boolean isOp(char c) {
        switch (c) {
            case '-':
            case '+':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    private static byte opPrior(char op) {
        switch (op) {
            case '*':
            case '/':
                return 2;
        }
        return 1;
    }

    private static double calculate(String sIn) {
        double dA = 0, dB = 0;
        String sTmp;
        Deque<Double> stack = new ArrayDeque<Double>();
        StringTokenizer st = new StringTokenizer(sIn);
        while(st.hasMoreTokens()) {
            sTmp = st.nextToken().trim();
            if (1 == sTmp.length() && isOp(sTmp.charAt(0))) {
                dB = stack.pop();
                dA = stack.pop();
                switch (sTmp.charAt(0)) {
                    case '+':
                        dA += dB;
                        break;
                    case '-':
                        dA -= dB;
                        break;
                    case '/':
                        if (dB == 0) {
                            stack.push(null);
                        } else {
                            dA /= dB;
                        }
                        break;
                    case '*':
                        dA *= dB;
                        break;
                }
                stack.push(dA);
            } else {
                dA = Double.parseDouble(sTmp);
                stack.push(dA);
            }
        }
        return stack.pop();

    }
}

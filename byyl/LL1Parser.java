import java.util.*;

public class LL1Parser {
    private Map<String, Map<String, String>> parsingTable;

    public LL1Parser() {
        // 初始化预测分析表
        parsingTable = new HashMap<>();

        // 填充预测分析表
        // E 行
        Map<String, String> rowE = new HashMap<>();
        rowE.put("(", "E -> TE'");
        rowE.put("number", "E -> TE'");
        parsingTable.put("E", rowE);

        // E' 行
        Map<String, String> rowEPrime = new HashMap<>();
        rowEPrime.put("+", "E' -> +TE'");
        rowEPrime.put(")", "E' -> ε");
        rowEPrime.put("$", "E' -> ε");
        parsingTable.put("E'", rowEPrime);

        // T 行
        Map<String, String> rowT = new HashMap<>();
        rowT.put("(", "T -> FT'");
        rowT.put("number", "T -> FT'");
        parsingTable.put("T", rowT);

        // T' 行
        Map<String, String> rowTPrime = new HashMap<>();
        rowTPrime.put("+", "T' -> ε");
        rowTPrime.put("*", "T' -> *FT'");
        rowTPrime.put(")", "T' -> ε");
        rowTPrime.put("$", "T' -> ε");
        parsingTable.put("T'", rowTPrime);

        // F 行
        Map<String, String> rowF = new HashMap<>();
        rowF.put("(", "F -> (E)");
        rowF.put("number", "F -> number");
        parsingTable.put("F", rowF);
    }

    public boolean parse(List<String> input) {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("$"); // 输入串的结束符
        stack.push("E"); // 开始符号

        int index = 0;
        while (!stack.isEmpty()) {
            String X = stack.pop();
            String currentInput = index < input.size() ? input.get(index) : "$";

            if (X.equals(currentInput)) {
                index++;
            } else if (!isNonTerminal(X)) {
                return false; // 错误情况：栈顶符号与输入不匹配
            } else {
                // 根据预测分析表获取产生式右部
                String production = parsingTable.get(X).get(currentInput);
                if (production == null) {
                    return false; // 错误情况：无法找到合适地产生式
                }

                // 将产生式右部 压入栈中（从右到左压入）
                String[] symbols = production.split(" ");
                for (int i = symbols.length - 1; i >= 0; i--) {
                    if (!symbols[i].equals("ε")) {
                        stack.push(symbols[i]);
                    }
                }
            }
        }

        return index == input.size();
    }

    private boolean isNonTerminal(String symbol) {
        // 判断是否为非终结符
        return symbol.equals("E") || symbol.equals("E'") || symbol.equals("T") ||
                symbol.equals("T'") || symbol.equals("F");
    }

    public static void main(String[] args) {
        LL1Parser parser = new LL1Parser();

        // 输入符号串
        List<String> input = new ArrayList<>(Arrays.asList("(", "number", "+", "number", ")", "$"));

        // 进行预测分析
        boolean result = parser.parse(input);

        // 输出分析结果
        if (result) {
            System.out.println("输入符号串符合文法规则！");
        } else {
            System.out.println("输入符号串不符合文法规则！");
        }
    }
}
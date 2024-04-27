import java.util.HashMap;

public class DFASimulator {
    public static void main(String[] args) {
        // 定义状态转移表
        HashMap<String, HashMap<Character, String>> transitions = new HashMap<>();
        HashMap<Character, String> sTransitions = new HashMap<>();
        sTransitions.put('a', "U");
        sTransitions.put('b', "V");
        transitions.put("S", sTransitions);

        HashMap<Character, String> uTransitions = new HashMap<>();
        uTransitions.put('a', "Q");
        uTransitions.put('b', "V");
        transitions.put("U", uTransitions);

        HashMap<Character, String> vTransitions = new HashMap<>();
        vTransitions.put('a', "U");
        vTransitions.put('b', "Q");
        transitions.put("V", vTransitions);

        HashMap<Character, String> qTransitions = new HashMap<>();
        qTransitions.put('a', "Q");
        qTransitions.put('b', "Q");
        transitions.put("Q", qTransitions);

        // 定义初始状态和接受状态
        String initialState = "S";
        String[] acceptingStates = {"Q", "V"};

        // 输入字符串
        String input = "ababab";

        // 模拟 DFA
        String currentState = initialState;
        for (char c : input.toCharArray()) {
            if (transitions.containsKey(currentState) && transitions.get(currentState).containsKey(c)) {
                currentState = transitions.get(currentState).get(c);
            } else {
                System.out.println("不是");
                return;
            }
        }

        if (isAcceptingState(currentState, acceptingStates)) {
            System.out.println("是");
        } else {
            System.out.println("不是");
        }
    }

    // 检查当前状态是否为接受状态
    private static boolean isAcceptingState(String state, String[] acceptingStates) {
        for (String acceptingState : acceptingStates) {
            if (state.equals(acceptingState)) {
                return true;
            }
        }
        return false;
    }
}


import java.util.ArrayList;
import java.util.List;

public class Solution {
       
    enum Bracket {
        T1('['),
        C1('('),
        S1('{'),
        T2(']'),
        C2(')'),
        S2('}');
        
        private final char value;
        
        private Bracket(char c) {
            this.value = c;
        }
        
        public static Bracket parse(char c) {
            if (c == T1.value) return T1;
            if (c == C1.value) return C1;
            if (c == S1.value) return S1;
            if (c == T2.value) return T2;
            if (c == C2.value) return C2;
            if (c == S2.value) return S2;
            throw new RuntimeException("Unknown bracket symbol:" + c);
        }
    }
    

    private final List<Bracket> opened = new ArrayList<>();

    int counterT = 0;
    int counterC = 0;
    int counterS = 0;

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        
        for (char c: s.toCharArray()) {
            Bracket b = Bracket.parse(c);
            if (opened.size() == 0) {
                if (b.equals(Bracket.C1)) {
                    counterC++;
                } else if (b.equals(Bracket.T1)) {
                    counterT++;
                } else if (b.equals(Bracket.S1)) {
                    counterS++;
                }
                opened.add(b);
            } else {
                final Bracket last = opened.get(opened.size()-1);
                
                if (b.equals(Bracket.C1)) {
                    counterC++;
                } else
                if (b.equals(Bracket.T1)) {
                    counterT++;
                } else
                if (b.equals(Bracket.S1)) {
                    counterS++;
                } else
                if (b.equals(Bracket.C2)) {
                    if (last.equals(Bracket.C1) && counterC > 0) {
                        counterC--;
                    } else {
                        return false;
                    }
                } else
                if (b.equals(Bracket.T2)) {
                    if (last.equals(Bracket.T1) && counterT > 0) {
                        counterT--;
                    } else {
                        return false;
                    }
                } else
                if (b.equals(Bracket.S2)) {
                    if (last.equals(Bracket.S1) && counterS > 0) {
                        counterS--;
                    } else {
                        return false;
                    }
                }
                
                if (b.equals(Bracket.C1) || b.equals(Bracket.T1) || b.equals(Bracket.S1)) {
                    opened.add(b);
                } else {
                    opened.remove(opened.size()-1);
                }
            
            }
        }
        
        return counterS == 0 && counterT == 0 && counterC == 0;
    }

}

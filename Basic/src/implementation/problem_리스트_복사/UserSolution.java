package implementation.problem_리스트_복사;

import java.util.HashMap;
import java.util.Map;

class UserSolution {

    private static final int MAX_LIST = 10;
    private static final int MAX_ADDRESS = 10_000;
    private static final int MAX_LENGTH = 200_000;
    private static final int MAX_UPDATE = 100_000;

    private int initIndex;
    private int[][] initLists;
    private int addressIndex;
    private Map<String, Integer> addressToIndex;
    private int updateIndex;
    private Log[] updateLog; // updateLog := updateIndex에서 일어난 변화를 기록
    private int[] prevUpdate; // prevUpdate[updateIndex] := updateIndex에서 이전에 일어난 변화를 기록한 updateIndex 탐색
    private int[] lastUpdate; //lastUpdate[addressIndex] := addressIndex에서 마지막에 일어난 변화를 기록한 updateIndex 탐색

    public void init() {
        this.initIndex = 0;
        this.initLists = new int[MAX_LIST][MAX_LENGTH];

        this.addressIndex = 0;
        this.addressToIndex = new HashMap<>();

        this.updateIndex = 0;
        this.updateLog = new Log[MAX_UPDATE];
        this.prevUpdate = new int[MAX_UPDATE];
        this.lastUpdate = new int[MAX_ADDRESS];
    }

    public void makeList(char mName[], int mLength, int mListValue[]) {
        String name = toString(mName);

        System.arraycopy(mListValue, 0, initLists[initIndex], 0, mLength);
        addressToIndex.put(name, addressIndex++);

        updateLog[updateIndex] = Log.makeList(initIndex++);
        prevUpdate[updateIndex] = -1;
        lastUpdate[addressToIndex.get(name)] = updateIndex++;
    }

    public void copyList(char mDest[], char mSrc[], boolean mCopy) {
        if (mCopy) {
            copyListDeeply(toString(mDest), toString(mSrc));
            return;
        }

        addressToIndex.put(toString(mDest), addressToIndex.get(toString(mSrc)));
    }

    private void copyListDeeply(String mDest, String mSrc) {
        addressToIndex.put(mDest, addressIndex++);

        updateLog[updateIndex] = Log.copyListDeeply();
        prevUpdate[updateIndex] = lastUpdate[addressToIndex.get(mSrc)];
        lastUpdate[addressToIndex.get(mDest)] = updateIndex++;
    }

    public void updateElement(char mName[], int mIndex, int mValue) {
        String name = toString(mName);

        updateLog[updateIndex] = Log.updateElement(mIndex, mValue);
        prevUpdate[updateIndex] = lastUpdate[addressToIndex.get(name)];
        lastUpdate[addressToIndex.get(name)] = updateIndex++;
    }

    public int element(char mName[], int mIndex) {
        String name = toString(mName);
        int curUpdateIndex = lastUpdate[addressToIndex.get(name)];

        while (true) {
            if (prevUpdate[curUpdateIndex] == -1) {
                return initLists[updateLog[curUpdateIndex].value][mIndex];
            }

            if (updateLog[curUpdateIndex].index == mIndex) {
                return updateLog[curUpdateIndex].value;
            }

            curUpdateIndex = prevUpdate[curUpdateIndex];
        }
    }

//    public int element(char mName[], int mIndex) {
//        String name = toString(mName);
//        int curUpdateIndex = lastUpdate[addressToIndex.get(name)];
//        return element(curUpdateIndex, mIndex);
//    }
//
//    public int element(int curIndex, int mIndex) {
//        if (prevUpdate[curIndex] == -1) {
//            return initLists[updateLog[curIndex].value][mIndex];
//        }
//
//        if (updateLog[curIndex].index == mIndex) {
//            return updateLog[curIndex].value;
//        }
//
//        return element(prevUpdate[curIndex], mIndex);
//    }

    private static class Log {
        private int index;
        private int value; // (index == -1) ? 원본 List의 index : 수정된 index의 value

        public Log(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public static Log makeList(int initIndex) {
            return new Log(-1, initIndex);
        }

        public static Log copyListDeeply() {
            return new Log(-1, -1);
        }

        public static Log updateElement(int updateIndex, int updateValue) {
            return new Log(updateIndex, updateValue);
        }
    }

    private String toString(char[] chars) {
        StringBuilder builder = new StringBuilder();

        for (char cur : chars) {
            if (cur == '\0') {
                break;
            }

            builder.append(cur);
        }

        return builder.toString();
    }

}
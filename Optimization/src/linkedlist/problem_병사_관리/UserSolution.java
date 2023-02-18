package linkedlist.problem_병사_관리;

public class UserSolution {

    private static class Soldier {
        private final int id;
        private int team;
        private Soldier next;
        private Soldier prev;

        public Soldier(int id, int team) {
            this.id = id;
            this.team = team;
            this.next = null;
            this.prev = null;
        }

        public Soldier setTeam(int team) {
            this.team = team;
            return this;
        }
    }

    private static class Team {
        private Soldier[] heads;
        private Soldier[] tails;

        public Team() {
            heads = new Soldier[6];
            tails = new Soldier[6];

            for (int i = 1; i <= 5; i++) {
                heads[i] = new Soldier(0, i);
                tails[i] = new Soldier(0, i);
                heads[i].next = tails[i];
                tails[i].prev = heads[i];
            }
        }
    }

    private Soldier[] soldiers;
    private Team[] teams;

    public void init() {
        soldiers = new Soldier[100_001];
        for (int i = 1; i <= 100_000; i++) {
            soldiers[i] = new Soldier(i, 0);
        }

        teams = new Team[6];
        for (int i = 1; i <= 5; i++) {
            teams[i] = new Team();
        }
    }

    public void hire(int mID, int mTeam, int mScore) {
        Soldier head = teams[mTeam].heads[mScore];
        Soldier hiring = soldiers[mID].setTeam(mTeam);

        hiring.next = head.next;
        head.next.prev = hiring;
        hiring.prev = head;
        head.next = hiring;
    }

    public void fire(int mID) {
        Soldier cur = soldiers[mID];
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        cur.next = null;
        cur.prev = null;
    }

    public void updateSoldier(int mID, int mScore) {
        fire(mID);
        hire(mID, soldiers[mID].team, mScore);
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        if (mChangeScore == 0) {
            return;
        }

        if (mChangeScore > 0) {
            plusTeamScore(mTeam, mChangeScore);
            return;
        }

        minusTeamScore(mTeam, mChangeScore);
    }

    public int bestSoldier(int mTeam) {
        Team team = teams[mTeam];
        int id = 0;

        for (int i = 5; i > 0; i--) {
            Soldier head = team.heads[i];
            Soldier tail = team.tails[i];

            for (Soldier cur = head.next; cur != tail; cur = cur.next) {
                id = Math.max(id, cur.id);
            }

            if (head.next != tail) {
                break;
            }
        }

        return id;
    }

    private void plusTeamScore(int mTeam, int mChangeScore) {
        Team team = teams[mTeam];

        for (int i = 4; i > 0; i--) {
            int changedScore = Math.min(5, i + mChangeScore);

            if (team.heads[i].next == team.tails[i]) {
                continue;
            }

            moveSoldiers(team.heads[i], team.tails[i], team.tails[changedScore]);
        }
    }

    private void minusTeamScore(int mTeam, int mChangeScore) {
        Team team = teams[mTeam];

        for (int i = 2; i <= 5; i++) {
           int changedScore = Math.max(1, i + mChangeScore);

           if (team.heads[i].next == team.tails[i]) {
               continue;
           }

           moveSoldiers(team.heads[i], team.tails[i], team.tails[changedScore]);
        }
    }

    private void moveSoldiers(Soldier head, Soldier tail, Soldier mTail) {
        mTail.prev.next = head.next;
        head.next.prev = mTail.prev;
        tail.prev.next = mTail;
        mTail.prev = tail.prev;

        head.next = tail;
        tail.prev = head;
    }

}
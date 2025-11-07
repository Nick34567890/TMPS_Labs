package lab2.domain.inventory;

import java.util.LinkedList;
import java.util.Queue;

public class AmmoClipPool {
    private static final int POOL_SIZE = 10;
    private final Queue<AmmoClip> pool = new LinkedList<>();

    public static class AmmoClip {
        public final int capacity;
        public int rounds;

        public AmmoClip(int capacity) {
            this.capacity = capacity;
            this.rounds = capacity;
        }

        void reload() { this.rounds = capacity; }

        public boolean fire() {
            if (rounds > 0) {
                rounds--;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Clip[" + rounds + "/" + capacity + "]";
        }
    }

    private AmmoClipPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.offer(new AmmoClip(30));
        }
    }

    private static class Holder {
        static final AmmoClipPool INSTANCE = new AmmoClipPool();
    }

    public static AmmoClipPool getInstance() {
        return Holder.INSTANCE;
    }

    public AmmoClip acquire() {
        AmmoClip clip = pool.poll();
        if (clip == null) {
            System.out.println("No clips available, creating new one.");
            clip = new AmmoClip(30);
        }
        return clip;
    }

    public void release(AmmoClip clip) {
        clip.reload();
        pool.offer(clip);
    }
}

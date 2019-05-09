package domain;

import java.util.Random;

public class Car {
        private final String name;
        private int position = 0;

        public Car(String name) {
                this.name = name;
        }

        public void movePosition() {
                if (drawRandomNumber()) {
                        position++;
                }

        }

        private boolean drawRandomNumber() { //뼈대 구축
                Random random = new Random();
                int n = random.nextInt(10);
                return n >= 4;
        }

        public int getPosition() {
                return this.position;
        }

        public String getName(){
                return this.name;
        }
}

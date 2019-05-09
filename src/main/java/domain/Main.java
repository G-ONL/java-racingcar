package domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

        static Car[] cars;
        static int n;
        static int counter = 0;
        static int max = -1;
        static String[] winner;

        public static void main(String args[]) throws IOException {
                inputNames();
                inputGameNumber();
                playRace();
                compare();
                outputWinner();

        }

        private static void inputNames() throws IOException {
                boolean flag = false;
                String[] name = {};
                while(!flag) {
                        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
                        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                        String names = bf.readLine();
                        name = names.split(",");
                        flag = valid(name);
                }
                makeCars(name);
        }

        private static boolean valid(String[] name){
                for(int i =0;i<name.length;i++){
                        if( 5 < name[i].length()){
                                System.out.println("자동차 이름은 5자 이하로만 작성해주세요.");
                                return false;
                        }
                }
                return true;
        }

        private static void makeCars(String[] name) {
                cars = new Car[name.length];
                for (int i = 0; i < name.length; i++) {
                        cars[i] = new Car(name[i]);
                }
        }

        private static void inputGameNumber() {
                System.out.println("시도할 회수는 몇회인가요?");
                Scanner scanner = new Scanner(System.in);
                n = scanner.nextInt();
                System.out.println();
        }

        private static void playRace() {
                System.out.println(" 실행 결과");
                for (int i = 0; i < n; i++) {
                        gameOnce();
                }
        }

        private static void gameOnce() {
                for (int i = 0; i < cars.length; i++) {
                        cars[i].movePosition();
                        outputPosition(cars[i]);
                }
                System.out.println();
        }

        private static void compare() {
                for (int i = 0; i < cars.length; i++) {
                        findMaximum(cars[i].getPosition());
                }
                winner = new String[counter];
                int j = 0;
                for (int i = 0; i < cars.length; i++) {
                        if (cars[i].getPosition() == max) {
                                winner[j] = cars[i].getName();
                                j++;
                        }
                }
        }

        private static void findMaximum(int pos) {
                if (pos > max) {
                        max = pos;
                        counter = 1;
                        return;
                }
                if (pos == max) {
                        counter++;
                        return;
                }
        }

        private static String arrayJoin(String glue, String array[]) {
                StringBuffer result = new StringBuffer();

                for (int i = 0; i < array.length; i++) {
                        result.append(array[i]);
                        if (i < array.length - 1) result.append(glue);
                }

                return result.toString();
        }

        private static void outputWinner() {
                String winnerName = arrayJoin(",", winner);
                System.out.println(winnerName + "가 최종 우승 했습니다.");
        }

        private static void outputPosition(Car car) {
                System.out.print(car.getName() + ":");
                for (int i = 0; i < car.getPosition(); i++) {
                        System.out.print("-");
                }
                System.out.println();
        }
}

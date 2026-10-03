import java.util.*;

public class ExaminationQuestionGrader {

    interface Question {
        double evaluate();
    }

    static class MCQ implements Question {
        private final String correctAnswer, studentAnswer;
        private final double points;

        MCQ(String correctAnswer, String studentAnswer, double points) {
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        public double evaluate() {
            return studentAnswer.equals(correctAnswer) ? points : 0.0;
        }
    }

    static class TF implements Question {
        private final String correctAnswer, studentAnswer;
        private final double points;

        TF(String correctAnswer, String studentAnswer, double points) {
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        public double evaluate() {
            return studentAnswer.equals(correctAnswer) ? points : 0.0;
        }
    }

    static class Essay implements Question {
        private final String correctAnswer, studentAnswer;
        private final double points;

        Essay(String correctAnswer, String studentAnswer, double points) {
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        public double evaluate() {
            String answer = studentAnswer.toLowerCase();
            String[] keywords = correctAnswer.split(",");
            int matched = 0;

            for (String keyword : keywords) {
                if (answer.contains(keyword.trim().toLowerCase())) {
                    matched++;
                }
            }

            if (matched >= 2) {
                return points * 0.75;
            } else if (matched == 1) {
                return points * 0.50;
            }
            return 0.0;
        }
    }

    private static String[] parseQuotedFields(String line) {
        List<String> fields = new ArrayList<>();
        java.util.regex.Matcher matcher =
                java.util.regex.Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line);

        while (matcher.find()) {
            fields.add(matcher.group(1) != null ? matcher.group(1) : matcher.group(2));
        }
        return fields.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = parseQuotedFields(line);

            String type = parts[0];
            String correctAnswer = parts[2];
            String studentAnswer = parts[3];
            double points = Double.parseDouble(parts[4]);

            Question question;

            switch (type) {
                case "MCQ":
                    question = new MCQ(correctAnswer, studentAnswer, points);
                    break;
                case "TF":
                    question = new TF(correctAnswer, studentAnswer, points);
                    break;
                default:
                    question = new Essay(correctAnswer, studentAnswer, points);
            }

            double score = question.evaluate();
            total += score;

            System.out.printf("%s: %.2f%n", type, score);
        }

        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}

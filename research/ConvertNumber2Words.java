package research;

public class ConvertNumber2Words {
    public static void main(String[] args) {
        System.out.println(convertNumber2Words(Long.valueOf("11308238273612833")));
    }

    private static String convertNumber2Words(Long amount) {
        String amountInWords = "";
        String[] unitNames = {"", "nghìn", "triệu", "tỷ"};
        int unitIndex = 0;

        while (amount > 0) {
            int number = Math.toIntExact(amount % 1000);
            String numberStr = String.valueOf(number);
            amount /= 1000;
            if (number > 0) {
                if (number < 100 && amount > 0) {
                    numberStr = "0" + numberStr;
                }
                amountInWords = readThreeDigitNumber(numberStr) + " " + unitNames[unitIndex] + " " + amountInWords;
            }
            if (amount > 0 && unitIndex == 3) {
                unitIndex = 0;
            }
            unitIndex++;
        }

        if (amountInWords.isEmpty()) {
            amountInWords = "Không ";
        }
        String firstLetter = amountInWords.substring(0, 1).toUpperCase();

        amountInWords = firstLetter + amountInWords.substring(1);

        amountInWords = amountInWords.trim() + " đồng ./.";
        return amountInWords.trim();
    }

    private static String readThreeDigitNumber(String numberStr) {
        int number = Integer.parseInt(numberStr);
        String[] digitNames = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
        String[] tenNames = {"", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
        String[] hundredNames = {"", "một trăm", "hai trăm", "ba trăm", "bốn trăm", "năm trăm", "sáu trăm", "bảy trăm", "tám trăm", "chín trăm"};

        int hundred = number / 100;
        int ten = (number % 100) / 10;
        int digit = number % 10;

        String result = "";

        if (hundred > 0) {
            result += hundredNames[hundred] + " ";
        } else if (numberStr.startsWith("0")) {
            result += "không trăm ";
        }

        if (ten > 0) {
            if (ten == 1 && digit > 0) {
                result += "mười " + digitNames[digit] + " ";
            } else {
                result += tenNames[ten] + " ";
                if (digit > 0) {
                    if (digit == 1) {
                        result += "mốt ";
                    } else if (digit == 5) {
                        result += "lăm ";
                    } else {
                        result += digitNames[digit] + " ";
                    }
                }
            }
        } else if (digit > 0) {
            if ((numberStr.startsWith("0") && number < 10) || (ten == 0 && hundred != 0)) {
                result += "linh ";
            }
            result += digitNames[digit] + " ";
        }

        return result.trim();
    }
}

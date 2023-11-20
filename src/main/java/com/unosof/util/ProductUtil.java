package com.unosof.util;

import com.unosof.entity.Inventory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductUtil {
  public static int DECIMAL_SCALE = 2;

  public static BigDecimal calculatePrice(Inventory inventory, BigDecimal markdown) {
    return inventory.getBasePrice()
        .subtract(inventory.getBasePrice().multiply(divide(markdown, BigDecimal.valueOf(100))));
  }

  //cubesPerBox = (width * height * length) / 1728;
  public static BigDecimal calculateCubesPerBox(Inventory inventory) {
    return divide(inventory.getBoxType().getWidth().multiply(inventory.getBoxType().getHeight())
        .multiply(inventory.getBoxType().getLength()), BigDecimal.valueOf(1728));
  }

  //outboundFreight = (cubesPerCarrier * cubesPerBox) / pack;
  public static BigDecimal calculateOutboundFreight(Inventory inventory, BigDecimal cubesPerBox) {
    return divide(inventory.getCubesPerCarrier().multiply(cubesPerBox),
        BigDecimal.valueOf(inventory.getPack()));
  }

  //finalFreight = outboundFreight * (freshCutValue/100)
  public static BigDecimal calculateFinalFreight(Inventory inventory, BigDecimal outboundFreight) {
    return outboundFreight.multiply(
        divide(inventory.getProduct().getFreshCutValue(), BigDecimal.valueOf(100)));
  }

  private static BigDecimal divide(BigDecimal number, BigDecimal divider) {
    return number.divide(divider, DECIMAL_SCALE, RoundingMode.HALF_UP);
  }

  public static String generateProductCode(String productName) {
    String[] words = productName.split(" ");
    StringBuilder productCodeBuilder = new StringBuilder();

    for (String word : words) {
      char firstChar = word.charAt(0);
      String wordWithoutSpecialCharacters = word.replaceAll("[^a-zA-Z0-9]", "");
      char lastChar =
          wordWithoutSpecialCharacters.charAt(wordWithoutSpecialCharacters.length() - 1);

      String middlePart = word.substring(1, word.length() - 1);
      int distinctChars = (int) middlePart.chars().distinct().count();

      Pattern notAlphanumericPattern = Pattern.compile("[^a-zA-Z0-9 ]");
      Matcher notAlphanumericMatcher = notAlphanumericPattern.matcher(word);
      String specialCharacters = "";
      while (notAlphanumericMatcher.find()) {
        specialCharacters = specialCharacters.concat(notAlphanumericMatcher.group());
      }
      productCodeBuilder.append(
          String.format("%c%d%s%c-", firstChar, distinctChars, specialCharacters, lastChar));
    }
    return productCodeBuilder.substring(0, productCodeBuilder.length() - 1);
  }
}

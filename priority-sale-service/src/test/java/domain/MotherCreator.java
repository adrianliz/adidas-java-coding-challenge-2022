package domain;

import com.github.javafaker.Faker;

public abstract class MotherCreator {
  private static final Faker faker = new Faker();

  public static Faker random() {
    return faker;
  }
}

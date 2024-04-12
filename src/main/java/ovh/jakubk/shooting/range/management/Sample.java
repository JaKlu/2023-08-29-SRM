package ovh.jakubk.shooting.range.management;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;

public class Sample {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("pawel"));
        System.out.println(LocalDate.now().toString());
    }
}

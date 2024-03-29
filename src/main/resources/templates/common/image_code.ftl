package ${packageName};

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author solom
 * @Description
 * @version: v1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 **/
public class ImageCode implements Serializable {

  private BufferedImage image;

  private String code;

  private LocalDateTime expireTime;

  public ImageCode(BufferedImage image, String code, int expireIn) {
    this.image = image;
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
    this.image = image;
    this.code = code;
    this.expireTime = expireTime;
  }

  public boolean isExpire() {
    return LocalDateTime.now().isAfter(expireTime);
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
  }
}
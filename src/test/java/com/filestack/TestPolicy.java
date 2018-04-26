package com.filestack;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Tests {@link Policy Policy} class to check building and converting to JSON.
 */
public class TestPolicy {
  private static final String TEST_SECRET = "RDOQO4WFRBFPRCFZOZFHJGZHW4";
  private static final String POLICY_NORMAL = "eyJleHBpcnkiOjQ2NTM2NTE2MDAsImNhbGxzIjpbIndyaXRlIiwicmVtb3ZlIl0sImhhbmRsZSI6IktXOUVKaFl0UzZ5NDhXaG0yUzZEIiwidXJsIjoiaHR0cHM6Ly91cGxvYWQud2lraW1lZGlhLm9yZy93aWtpcGVkaWEvLioiLCJtYXhTaXplIjoxMDI0LCJtaW5TaXplIjoxMjgsInBhdGgiOiIvc29tZS9kaXIvIiwiY29udGFpbmVyIjoic29tZS1jb250YWluZXIifQ";
  private static final String SIGNATURE_NORMAL = "881aefee018d7ab43da7b9dd1887fbd0a81368c2c3542902925066581a3f8135";
  private static final String POLICY_NO_CALLS = "eyJleHBpcnkiOjQ2NTM2NTE2MDB9";
  private static final String SIGNATURE_NO_CALLS
      = "ab1bea1b6ce36f77ff2a0a4da25651e64dfd9daeb0b2eacfe3836a13c96c022c";
  private static final String POLICY_FULL = "eyJleHBpcnkiOjE1NDA5MjUyMzYsImNhbGxzIjpbInBpY2siLCJyZ"
      + "WFkIiwic3RhdCIsIndyaXRlIiwid3JpdGVVcmwiLCJzdG9yZSIsImNvbnZlcnQiLCJyZW1vdmUiLCJleGlmIl19";
  private static final String SIGNATURE_FULL
      = "589352c67bec6999108060af07027175cade89db4a1296f7f7a603df72240edc";

  @Test
  public void testNormal() {
    Gson gson = new Gson();

    Policy policy = new Policy.Builder()
        .expiry(4653651600L)
        .calls(Policy.CALL_WRITE, Policy.CALL_REMOVE)
        .handle("KW9EJhYtS6y48Whm2S6D")
        .url("https://upload.wikimedia.org/wikipedia/.*")
        .maxSize(1024)
        .minSize(128)
        .path("/some/dir/")
        .container("some-container")
        .build(TEST_SECRET);

    Assert.assertEquals(POLICY_NORMAL, policy.getEncodedPolicy());
    Assert.assertEquals(SIGNATURE_NORMAL, policy.getSignature());
  }

  @Test
  public void testNoCalls() {
    Gson gson = new Gson();

    Policy policy = new Policy.Builder()
        .expiry(4653651600L)
        .build(TEST_SECRET);

    Assert.assertEquals(POLICY_NO_CALLS, policy.getEncodedPolicy());
    Assert.assertEquals(SIGNATURE_NO_CALLS, policy.getSignature());
  }
}

// MOCK IMPLEMENTATION FOR DEV
// Replace with real reCAPTCHA v3 later

export const executeCaptcha = async () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve("mock-captcha-token");
    }, 300);
  });
};

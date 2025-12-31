import { useState } from "react";
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";

export default function AuthPage({ onLogin }) {
  const [mode, setMode] = useState("login");

  return mode === "login" ? (
    <LoginPage
      onLogin={onLogin}
      goRegister={() => setMode("register")}
      goForgot={() => setMode("forgot")}
    />
  ) : (
    <RegisterPage goLogin={() => setMode("login")} />
  );
}

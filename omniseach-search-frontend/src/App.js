import { useState } from "react";
import AuthPage from "./pages/AuthPage";
import SearchPage from "./pages/SearchPage";
import { getAccessToken } from "./utils/tokenStorage";

export default function App() {
  const [loggedIn, setLoggedIn] = useState(!!getAccessToken());
  return loggedIn ? <SearchPage /> : <AuthPage onLogin={() => setLoggedIn(true)} />;
}

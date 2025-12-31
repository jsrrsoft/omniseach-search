import { Button } from "@mui/material";
import { clearTokens } from "../utils/tokenStorage";

export default function LogoutButton() {
  return (
    <Button
      color="inherit"
      onClick={() => {
        clearTokens();
        window.location.href = "/";
      }}
    >
      Logout
    </Button>
  );
}

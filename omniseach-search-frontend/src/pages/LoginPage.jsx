import { useState } from "react";
import { Box, Button, Container, TextField, Paper, Typography } from "@mui/material";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { login } from "../auth/authService";
import { generateCaptcha } from "../utils/captcha";

export default function LoginPage({ onLogin, goRegister }) {
  const [form, setForm] = useState({ username: "", password: "", captcha: "" });
  const [captcha] = useState(generateCaptcha());

  const submit = async () => {
    await login({ ...form, captcha });
    onLogin();
  };

  return (
    <Box minHeight="100vh" display="flex" flexDirection="column">
      <Header />
      <Container maxWidth="sm" sx={{ flexGrow: 1 }}>
        <Paper sx={{ p: 4, mt: 8 }}>
          <Typography variant="h5">Login</Typography>

          <TextField label="Username" fullWidth margin="normal"
            onChange={(e) => setForm({ ...form, username: e.target.value })} />

          <TextField label="Password" type="password" fullWidth margin="normal"
            onChange={(e) => setForm({ ...form, password: e.target.value })} />

          <Typography>Captcha: <b>{captcha}</b></Typography>
          <TextField label="Enter Captcha" fullWidth margin="normal"
            onChange={(e) => setForm({ ...form, captcha: e.target.value })} />

          <Button fullWidth variant="contained" sx={{ mt: 2 }} onClick={submit}>
            Login
          </Button>

          <Button fullWidth sx={{ mt: 1 }} onClick={goRegister}>
            Register
          </Button>
        </Paper>
      </Container>
      <Footer />
    </Box>
  );
}

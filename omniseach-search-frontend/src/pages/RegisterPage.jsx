import { useState } from "react";
import { Box, Button, Container, TextField, Paper, Typography } from "@mui/material";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { register } from "../auth/authService";
import { generateCaptcha } from "../utils/captcha";
import PasswordStrength from "../components/passwordStrength";

export default function RegisterPage({ goLogin }) {
  const [form, setForm] = useState({
    fullName: "", username: "", email: "", password: "", confirm: "", captcha: ""
  });
  const [captcha] = useState(generateCaptcha());

  const submit = async () => {
    if (form.password !== form.confirm) return alert("Passwords do not match");
    if (form.captcha !== captcha) return alert("Invalid captcha");

    await register({ ...form, captcha });
    alert("Registered. Verify email.");
    goLogin();
  };

  return (
    <Box minHeight="100vh" display="flex" flexDirection="column">
      <Header />
      <Container maxWidth="sm" sx={{ flexGrow: 1 }}>
        <Paper sx={{ p: 4, mt: 4 }}>
          <Typography variant="h5">Register</Typography>

          {["fullName","username","email"].map(f => (
            <TextField key={f} label={f} fullWidth margin="normal"
              onChange={(e) => setForm({ ...form, [f]: e.target.value })} />
          ))}

          <TextField type="password" label="Password" fullWidth margin="normal"
            onChange={(e) => setForm({ ...form, password: e.target.value })} />

          <PasswordStrength password={form.password} />

          <TextField type="password" label="Confirm Password" fullWidth margin="normal"
            onChange={(e) => setForm({ ...form, confirm: e.target.value })} />

          <Typography>Captcha: <b>{captcha}</b></Typography>
          <TextField label="Enter Captcha" fullWidth margin="normal"
            onChange={(e) => setForm({ ...form, captcha: e.target.value })} />

          <Button fullWidth variant="contained" sx={{ mt: 2 }} onClick={submit}>
            Register
          </Button>

          <Button fullWidth sx={{ mt: 1 }} onClick={goLogin}>
            Back to Login
          </Button>
        </Paper>
      </Container>
      <Footer />
    </Box>
  );
}

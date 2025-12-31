import { LinearProgress, Typography } from "@mui/material";
import { strengthScore } from "../utils/passwordStrength";

export default function PasswordStrength({ password }) {
  const score = strengthScore(password);
  const labels = ["Weak", "Fair", "Good", "Strong"];

  return (
    <>
      <LinearProgress variant="determinate" value={score * 25} />
      <Typography variant="caption">
        Strength: {labels[Math.max(score - 1, 0)]}
      </Typography>
    </>
  );
}

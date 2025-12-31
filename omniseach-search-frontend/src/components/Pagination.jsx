import { Button } from "@mui/material";

export default function Pagination({ page, totalPages, onChange }) {
  return (
    <div>
      <Button
        disabled={page === 0}
        onClick={() => onChange(page - 1)}
      >
        Prev
      </Button>

      <span> Page {page + 1} of {totalPages} </span>

      <Button
        disabled={page + 1 >= totalPages}
        onClick={() => onChange(page + 1)}
      >
        Next
      </Button>
    </div>
  );
}

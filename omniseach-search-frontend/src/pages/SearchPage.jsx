import { useEffect, useState } from "react";
import api from "../api/axiosInstance";
import SearchBar from "../components/SearchBar";
import Results from "../components/Results";
import Pagination from "../components/Pagination";

export default function SearchPage() {
  const [query, setQuery] = useState("");
  const [data, setData] = useState(null);
  const [page, setPage] = useState(0);

  useEffect(() => {
    if (!query) return;

    api
      .get("/api/search", {
        params: { q: query, page, size: 5 },
      })
      .then((res) => setData(res.data));
  }, [query, page]);

  return (
    <div style={{ padding: 40 }}>
      <SearchBar value={query} onChange={setQuery} />
      <Results data={data} />
      {data?.messages && (
        <Pagination
          page={page}
          totalPages={data.messages.totalPages}
          onChange={setPage}
        />
      )}
    </div>
  );
}

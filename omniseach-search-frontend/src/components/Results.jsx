export default function Results({ data }) {
  if (!data) return null;

  return (
    <>
      <h3>Messages</h3>
      {data.messages?.content?.map((m) => (
        <p key={m.id}>{m.content}</p>
      ))}
    </>
  );
}

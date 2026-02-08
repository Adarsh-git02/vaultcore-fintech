import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav style={{ padding: 15, background: "#111", color: "#fff" }}>
      <Link to="/dashboard" style={{ marginRight: 15, color: "#0f0" }}>
        Dashboard
      </Link>
      <Link to="/transactions" style={{ color: "#0f0" }}>
        Transactions
      </Link>
    </nav>
  );
}

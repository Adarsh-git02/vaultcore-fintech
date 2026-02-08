import api from "../api/axios";
import { useEffect, useState } from "react";

export default function Transactions() {
  const [txs, setTxs] = useState([]);

  useEffect(() => {
    api.get("/transactions/me").then(res => setTxs(res.data));
  }, []);

  return (
    <div>
      <h2>Transactions</h2>
      <ul>
        {txs.map((t, i) => (
          <li key={i}>
            {t.type} — ₹{t.amount} — {t.timestamp}
          </li>
        ))}
      </ul>
    </div>
  );
}

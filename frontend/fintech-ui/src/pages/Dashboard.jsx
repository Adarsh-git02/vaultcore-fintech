import api from "../api/axios";
import { useEffect, useState } from "react";

export default function Dashboard() {
  const [account, setAccount] = useState(null);
  const [amount, setAmount] = useState("");

  useEffect(() => {
    api.get("/accounts/me").then(res => setAccount(res.data));
  }, []);

  const deposit = async () => {
    await api.post(`/accounts/deposit?amount=${amount}`);
    window.location.reload();
  };

  const withdraw = async () => {
    await api.post(`/accounts/withdraw?amount=${amount}`);
    window.location.reload();
  };

  if (!account) return <div>Loading...</div>;

  return (
    <div>
      <h2>Dashboard</h2>
      <p>Account: {account.accountNumber}</p>
      <p>Balance: ₹{account.balance}</p>

      <input placeholder="Amount" onChange={e => setAmount(e.target.value)} />
      <button onClick={deposit}>Deposit</button>
      <button onClick={withdraw}>Withdraw</button>
    </div>
  );
}

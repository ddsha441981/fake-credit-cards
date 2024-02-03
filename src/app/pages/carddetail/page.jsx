"use client";
import {useEffect, useState} from "react";
import {BASE_URL_API} from "@/config/helper";

const CardDetail = () => {

    // fetch data
    const [cards, setCards] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchAllCards = async () => {
            try {
                const response = await fetch(BASE_URL_API + "/all", {
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                const cardsList = await response.json();
                setCards(cardsList);
            } catch (error) {
                console.error("Error fetching data:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchAllCards();
    }, []);

    return (
        <>
            <div className="container-fluid">
                <h2>Cards</h2>

                <table className="table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Card Holder Name</th>
                        <th>Card Number</th>
                        <th>Card Type</th>
                        <th>Bank</th>
                        <th>Expiry</th>
                        <th>CVV</th>
                    </tr>
                    </thead>
                    <tbody>
                    {loading ? (
                        <tr>
                            <td colSpan="3">Loading...</td>
                        </tr>
                    ) : (
                        cards.map((card, index) => (
                            <tr key={index} className="table-primary">
                                <td>{card.id}</td>
                                <td>{card.cardholder}</td>
                                <td>{card.cardNumber}</td>
                                <td>{card.company}</td>
                                <td>{card.bank}</td>
                                <td>{card.expiryDate}</td>
                                <td>{card.cvv}</td>
                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            </div>
        </>
    );
};
export default CardDetail;

"use client";
import {useEffect, useState} from "react";
import {BASE_URL_API} from "@/config/helper";

const Card = () =>{


// fetch data
    const [fetchData, setFetchData] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchCards = async () => {
            setLoading(true);
            try {
                const response = await fetch(BASE_URL_API + "/latest", {
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                const cards = await response.json();
                setFetchData(cards);
            } catch (error) {
                console.error("Error fetching data:", error);
            } finally {
                setLoading(false);
            }
        }

        fetchCards();
    }, []);

    // field
    const [data, setData] = useState({
        cardholder: '',
        cardCount: '',
        bank: '',
        company: ''
    });

    // error
    const [error, setError] = useState({
        errors: {},
        isError: false
    });

    // handle change
    const handleChange = (event) => {
        const value = event.target.value;

        setData({
            ...data, [event.target.name]: value
        });
    }

    const saveCard = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(BASE_URL_API, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                throw new Error("Something went wrong");
            }

            const newUser = await response.json();
            console.log("New user:", newUser);

            // If you want to update the displayed data after submitting, you can refetch it
            const updatedResponse = await fetch(BASE_URL_API + "/latest", {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const updatedData = await updatedResponse.json();
            setFetchData(updatedData);
        } catch (error) {
            console.error("Error saving data:", error);
        }
    }

    return(

        <>
           <div className="container-fluid">

               <div className="container">
                   <h1>Card</h1>
                       <label htmlFor="cardCount" className="form-label">Select list (select one):</label>
                   <select className="form-select" id="cardCount" name="cardCount" onChange={handleChange}>
                       <option value={1}>1</option>
                       <option value={2}>2</option>
                       <option value={3}>3</option>
                       <option value={4}>4</option>
                   </select>


                   <label htmlFor="bank" className="form-label">Select Bank (select one):</label>
                       <select className="form-select" id="bank" name="bank" onChange={handleChange}>
                           {/*<option disabled="true">Select Bank</option>*/}
                           <option value="STATE_BANK_OF_INDIA">State Bank of India</option>
                           <option value="PUNJAB_NATIONAL_BANK">Punjab National Bank</option>
                           <option value="BANK_OF_BARODA">Bank of Baroda</option>
                           <option value="CANARA_BANK">Canara Bank</option>
                           <option value="UNION_BANK_OF_INDIA">Union Bank of India</option>
                           <option value="BANK_OF_INDIA">Bank of India</option>
                           <option value="CENTRAL_BANK_OF_INDIA">Central Bank of India</option>
                           <option value="INDIAN_BANK">Indian Bank</option>
                           <option value="INDIAN_OVERSEAS_BANK">Indian Overseas Bank</option>
                           <option value="BANK_OF_MAHARASHTRA">Bank of Maharashtra</option>
                           <option value="ICICI_BANK">ICICI Bank</option>
                           <option value="HDFC_BANK">HDFC Bank</option>
                           <option value="AXIS_BANK">Axis Bank</option>
                           <option value="KOTAK_MAHINDRA_BANK">Kotak Mahindra Bank</option>
                           <option value="INDUSIND_BANK">IndusInd Bank</option>
                           <option value="YES_BANK">Yes Bank</option>
                           <option value="RBL_BANK">RBL Bank</option>
                           <option value="FEDERAL_BANK">Federal Bank</option>
                           <option value="IDFC_FIRST_BANK">IDFC First Bank</option>
                           <option value="BANDHAN_BANK">Bandhan Bank</option>
                           <option value="BARODA_UTTAR_PRADESH_GRAMIN_BANK">Baroda Uttar Pradesh Gramin Bank</option>
                           <option value="PUNJAB_GRAMIN_BANK">Punjab Gramin Bank</option>
                           <option value="KERALA_GRAMIN_BANK">Kerala Gramin Bank</option>
                           <option value="PRAGATHI_KRISHNA_GRAMIN_BANK">Pragathi Krishna Gramin Bank</option>
                           <option value="ANDHRA_PRAGATHI_GRAMEENA_BANK">Andhra Pragathi Grameena Bank</option>
                           <option value="PALLAVAN_GRAMA_BANK">Pallavan Grama Bank</option>
                           <option value="UTTAR_BANGA_KSHETRIYA_GRAMIN_BANK">Uttar Banga Kshetriya Gramin Bank</option>
                           <option value="ASSAM_GRAMIN_VIKASH_BANK">Assam Gramin Vikash Bank</option>
                           <option value="MADHYA_BIHAR_GRAMIN_BANK">Madhya Bihar Gramin Bank</option>
                           <option value="ODISHA_GRAMYA_BANK">Odisha Gramya Bank</option>
                       </select>

                       <label htmlFor="company" className="form-label">Credit Type (select one):</label>
                       <select className="form-select" id="company" name="company" onChange={handleChange}>
                           {/*<option disabled="true">Select Company</option>*/}
                           <option>MASTERCARD</option>
                           <option>VISA</option>
                           <option>RUPAY</option>
                           <option>AMERICAN_EXPRESS</option>
                       </select>
                       <div className="mb-3 mt-3">
                           <label htmlFor="cardholder" className="form-label">Card Holder Name:</label>
                           <input type="text" onChange={(e)=>handleChange(e)} value={data.cardholder} className="form-control" id="cardholder" placeholder="Enter Card Holder Name" name="cardholder"/>
                       </div>
                       <button type="submit" onClick={saveCard} className="btn btn-primary">Submit</button>
               </div>
           </div>





            <div className="container-fluid">
               <div className="container">
                   <div>
                       <h1>Fetched Data:</h1>
                       {loading ? (
                           <p>Loading...</p>
                       ) : (
                           <div className="credit-card-container">
                               {fetchData.map((card) => (
                                   <div key={card.id} className="credit-card">
                                       <div className="card-number">{card.cardNumber}</div>
                                       <div className="cardholder">{card.cardholder}</div>
                                       <div className="expiration">{card.expiryDate}</div>
                                       <div className="company">{card.company}</div>
                                       <div className="bank">{card.bank}</div>

                                   </div>
                               ))};
                           </div>
                       )}
                   </div>

               </div>
            </div>

        </>
    );
};
export default Card;
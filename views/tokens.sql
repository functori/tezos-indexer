 SELECT contracts."Address" AS contract,
    tokens."TokenId" AS token_id
   FROM "Tokens" tokens
     LEFT JOIN "Accounts" contracts ON contracts."Id" = tokens."ContractId";
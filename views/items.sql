with tokens as (
    SELECT
        tokens."Id" as tid,
        contract."Address" || ':' || tokens."TokenId" AS "id",
        contract."Address" AS "contract",
        tokens."TokenId" AS "tokenId",
        contract."Address" AS "creators",
        tokens."TotalSupply" AS  "supply",
        tokens."TotalBurned" AS  "burned",
        block."Timestamp" AS "mintedAt",
        block."Timestamp" AS "date",
        tokens."Metadata" AS "meta"
    FROM "Tokens" tokens
    LEFT JOIN "Accounts" contract ON contract."Id" = tokens."ContractId"
    LEFT JOIN "Blocks" block ON block."Level" = tokens."FirstLevel"
),
owners as (
    SELECT
        tb."TokenId" as tid,
        string_agg(acc."Address", ', ') as owners
    FROM "TokenBalances" AS tb
    LEFT JOIN "Accounts" AS acc ON acc."Id" = tb."AccountId"
    WHERE tb."TokenId" = ANY(select tid from tokens) AND tb."Balance" != '0'
    GROUP BY tb."TokenId"
)

SELECT 
    tokens."id",
    tokens."contract",
    tokens."tokenId",
    tokens."creators",
    tokens."supply",
    tokens."burned",
    owners."owners",
    tokens."mintedAt",
    tokens."date",
    tokens."meta"
FROM owners
LEFT JOIN tokens ON tokens.tid = owners.tid
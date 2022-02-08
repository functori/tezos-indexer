 WITH items AS (
         SELECT
                CASE
                    WHEN transfer."FromId" IS NULL THEN 'MINT'::text
                    WHEN transfer."ToId" IS NULL OR transfer."ToId" = 1014250 THEN 'BURN'::text
                    ELSE 'TRANSFER'::text
                END AS "type",
            fromacc."Address" AS "from",
            COALESCE(toacc."Address", fromacc."Address") AS "to",
            contract."Address" AS "contract",
            token."TokenId" as "token_id",
            transfer."Amount" AS "value",
            COALESCE(transaction."OpHash", origination."OpHash") AS "tx_hash",
            block."Hash" AS "block_hash",
            transfer."Level" AS "block_number",
            transfer."Id" AS "log_index"
           FROM "TokenTransfers" transfer
             LEFT JOIN "Tokens" token ON token."Id" = transfer."TokenId"
             LEFT JOIN "Accounts" contract ON contract."Id" = token."ContractId"
             LEFT JOIN "Accounts" fromacc ON fromacc."Id" = transfer."FromId"
             LEFT JOIN "Accounts" toacc ON toacc."Id" = transfer."ToId"
             LEFT JOIN "Blocks" block ON block."Level" = transfer."Level"
             LEFT JOIN "TransactionOps" transaction ON transaction."Id" = transfer."TransactionId"
             LEFT JOIN "OriginationOps" origination ON origination."Id" = transfer."OriginationId"
        )
 SELECT items."type",
    items."from",
    items."to",
    items."contract",
    items."token_id",
    items."value",
    items."tx_hash",
    items."block_hash",
    items."block_number",
    items."log_index"
   FROM items;
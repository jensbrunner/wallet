package org.example

data class Claim(
    val value: Any,
    val selectivelyDisclosable: Boolean
)

data class Credential(
    val id: String,
    val issuer: String,
    val type: String,
    val issuesAt: Long,
    val expiresAt: Long?,
    val claims: Map<String, Claim>
)

data class PresentationRequest(
    val nonce: String,
    val audience: String,
    val requiredClaim: List<ClaimRequirement>
)

data class ClaimRequirement(
    val claimName: String,
    val acceptedCredentialTypes: Set<String>,
    val requiredIssuer: String? = null
)

data class SelectedCredential(
    val credentialId: String,
    val disclosedClaims: Set<String>
)

sealed class PresentationPlan {
    data class Satisfied(
        val none: String,
        val audience: String,
        val selectedCredentials: List<SelectedCredential>
    ) : PresentationPlan()
    
    data class Unsatisfied(
        val missingClaims: Set<String>,
        val untrustedCredentials: Set<String>,
        val expiredCredentials: Set<String>,
        val nonDisclosableClaims: Set<String>
    ) : PresentationPlan()
}

data class Wallet(
    val credentials: List<Credential>
)
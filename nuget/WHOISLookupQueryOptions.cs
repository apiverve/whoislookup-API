using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.WHOISLookup
{
    /// <summary>
    /// Query options for the WHOIS Lookup API
    /// </summary>
    public class WHOISLookupQueryOptions
    {
        /// <summary>
        /// The domain name for which you want to get the registration data (e.g., myspace.com)
        /// Example: myspace.com
        /// </summary>
        [JsonProperty("domain")]
        public string Domain { get; set; }
    }
}

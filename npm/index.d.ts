declare module '@apiverve/whoislookup' {
  export interface whoislookupOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface whoislookupResponse {
    status: string;
    error: string | null;
    data: WHOISLookupData;
    code?: number;
  }


  interface WHOISLookupData {
      domainStatus:               string[];
      domainName:                 string;
      registryDomainID:           string;
      registrarWHOISServer:       string;
      registrarURL:               string;
      updatedDate:                Date;
      createdDate:                Date;
      expiryDate:                 Date;
      registrar:                  string;
      registrarIANAID:            string;
      registrarAbuseContactEmail: string;
      registrarAbuseContactPhone: string;
      dNSSEC:                     string;
  }

  export default class whoislookupWrapper {
    constructor(options: whoislookupOptions);

    execute(callback: (error: any, data: whoislookupResponse | null) => void): Promise<whoislookupResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: whoislookupResponse | null) => void): Promise<whoislookupResponse>;
    execute(query?: Record<string, any>): Promise<whoislookupResponse>;
  }
}

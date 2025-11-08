declare module '@apiverve/whoislookup' {
  export interface whoislookupOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface whoislookupResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class whoislookupWrapper {
    constructor(options: whoislookupOptions);

    execute(callback: (error: any, data: whoislookupResponse | null) => void): Promise<whoislookupResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: whoislookupResponse | null) => void): Promise<whoislookupResponse>;
    execute(query?: Record<string, any>): Promise<whoislookupResponse>;
  }
}

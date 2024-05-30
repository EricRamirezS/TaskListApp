// @vitest-environment node

import { createServer } from 'vite';

describe('App', () => {
    let server;

    beforeAll(async () => {
        server = await createServer();
        await server.listen();
    });

    afterAll(async () => {
        await server.close();
    });

    it('should render the app without errors', async () => {
        const { port } = server.httpServer.address();
        const response = await fetch(`http://localhost:${port}`);
        expect(response.ok).toBe(true);
        const text = await response.text();
        expect(text).toContain('<div id="root">');
    });
});
import { test, expect } from '@playwright/test';

test.beforeEach(async ({ page }) => {
  await page.goto(process.env['UI_HOST']!);

  await expect(page).toHaveTitle('Velocity Template Tester');
});

test.describe('Test template', () => {
  test('Pass data model as properties', async ({ page }) => {
    await page.locator('#template').fill('Hello ${user}!');
    await page.locator('#dataModel').fill('user=World');
    await page.locator('#eval-btn').click();

    await expect(page.locator('#result')).toHaveValue('Hello World!');
  });

  test('Pass data model as JSON', async ({ page }) => {
    await page.locator('#template').fill('Hello ${user}!');
    await page.locator('#dataModel').fill(`{"user":"World"}`);
    await page.locator('#eval-btn').click();

    await expect(page.locator('#result')).toHaveValue('Hello World!');    
  });
});
